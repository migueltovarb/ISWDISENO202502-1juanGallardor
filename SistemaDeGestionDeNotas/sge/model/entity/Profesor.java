package com.universidad.sge.model.entity;

import com.universidad.sge.model.enums.RolEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "usuarios")
public class Profesor extends Usuario {
    
    private List<String> cursosAsignados;
    
    public Profesor() {
        super();
        this.cursosAsignados = new ArrayList<>();
    }
    
    public Profesor(String nombre, String apellido, String email, String username, String password) {
        super(nombre, apellido, email, username, password, RolEnum.PROFESOR);
        this.cursosAsignados = new ArrayList<>();
    }
    
    @Override
    public List<String> getPermisosEspecificos() {
        return Arrays.asList(
            "VER_ESTUDIANTES_CURSO",
            "CREAR_EVALUACION",
            "REGISTRAR_NOTA",
            "EDITAR_NOTA",
            "GENERAR_REPORTE_CURSO"
        );
    }
    
    public void agregarCurso(String cursoId) {
        this.cursosAsignados.add(cursoId);
    }
    
    public List<String> getCursosAsignados() {
        return cursosAsignados;
    }
    
    public void setCursosAsignados(List<String> cursosAsignados) {
        this.cursosAsignados = cursosAsignados;
    }
}