package com.universidad.sge.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cursos")
public class Curso {
    
    @Id
    private String id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String profesorId;
    private List<String> estudiantesInscritos;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    
    public Curso() {
        this.estudiantesInscritos = new ArrayList<>();
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public void generarCodigo() {
        int year = LocalDateTime.now().getYear();
        int random = (int)(Math.random() * 1000);
        this.codigo = "CUR-" + year + "-" + String.format("%03d", random);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getProfesorId() {
        return profesorId;
    }
    
    public void setProfesorId(String profesorId) {
        this.profesorId = profesorId;
    }
    
    public List<String> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }
    
    public void setEstudiantesInscritos(List<String> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}