package com.universidad.sge.service;

import com.universidad.sge.dto.LoginRequest;
import com.universidad.sge.dto.LoginResponse;
import com.universidad.sge.dto.UsuarioDTO;
import com.universidad.sge.model.entity.Sesion;
import com.universidad.sge.model.entity.Usuario;
import com.universidad.sge.repository.SesionRepository;
import com.universidad.sge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SesionRepository sesionRepository;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = null;

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            usuario = usuarioRepository.findByUsername(request.getUsername()).orElse(null);
        } else if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            usuario = usuarioRepository.findByEmail(request.getEmail()).orElse(null);
        }

        if (usuario == null || !usuario.validarPassword(request.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        String token = UUID.randomUUID().toString();

        Sesion sesion = new Sesion();
        sesion.setUsuarioId(usuario.getId());
        sesion.setToken(token);
        sesionRepository.save(sesion);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol().toString());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsuario(usuarioDTO);
        response.setExpiracion(sesion.getFechaExpiracion());

        return response;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerPorId(String id) {
        return usuarioRepository.findById(id);
    }
}