package com.universidad.sge.config;

import com.universidad.sge.model.entity.Administrador;
import com.universidad.sge.model.entity.Estudiante;
import com.universidad.sge.model.entity.Profesor;
import com.universidad.sge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.count() == 0) {

            Administrador admin = new Administrador("Admin", "Sistema", "admin@universidad.edu.co", "admin", "admin123");
            usuarioRepository.save(admin);
            System.out.println("Administrador creado - username: admin, password: admin123");

            Profesor profesor = new Profesor("Maria", "Gonzalez", "maria.gonzalez@universidad.edu.co", "maria.gonzalez", "profesor123");
            usuarioRepository.save(profesor);
            System.out.println("Profesor creado - username: maria.gonzalez, password: profesor123");

            Estudiante estudiante = new Estudiante("Juan", "Perez", "juan.perez@universidad.edu.co", "juan.perez", "estudiante123");
            usuarioRepository.save(estudiante);
            System.out.println("Estudiante creado - username: juan.perez, password: estudiante123");

            System.out.println("Datos de prueba cargados exitosamente");
        }
    }
}
