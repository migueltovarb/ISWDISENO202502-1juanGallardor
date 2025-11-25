package com.universidad.sge.dto;

import java.time.LocalDateTime;

public class LoginResponse {

    private String token;
    private UsuarioDTO usuario;
    private LocalDateTime expiracion;

    public LoginResponse() {
    }

    public LoginResponse(String token, UsuarioDTO usuario, LocalDateTime expiracion) {
        this.token = token;
        this.usuario = usuario;
        this.expiracion = expiracion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(LocalDateTime expiracion) {
        this.expiracion = expiracion;
    }
}
