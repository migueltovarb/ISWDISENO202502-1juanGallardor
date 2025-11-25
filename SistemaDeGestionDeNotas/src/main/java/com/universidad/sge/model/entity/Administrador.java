package com.universidad.sge.model.entity;

import com.universidad.sge.model.enums.RolEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Arrays;
import java.util.List;

@Document(collection = "usuarios")
public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String nombre, String apellido, String email, String username, String password) {
        super(nombre, apellido, email, username, password, RolEnum.ADMIN);
    }

    @Override
    public List<String> getPermisosEspecificos() {
        return Arrays.asList(
            "CREAR_USUARIO",
            "LISTAR_USUARIOS",
            "CREAR_CURSO",
            "ASIGNAR_PROFESOR",
            "ASIGNAR_ESTUDIANTES",
            "GENERAR_REPORTES_ACADEMICOS"
        );
    }
}