package com.universidad.sge.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "evaluaciones")
public class Evaluacion {
    
    @Id
    private String id;
    private String nombre;
    private Double porcentaje;
    private String descripcion;
    private LocalDate fecha;
    private String cursoId;
    private LocalDateTime fechaCreacion;
    
    public Evaluacion() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public boolean validarPorcentaje() {
        return porcentaje != null && porcentaje >= 1.0 && porcentaje <= 100.0;
    }

    public boolean validarNombre() {
        return nombre != null && !nombre.trim().isEmpty();
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Double getPorcentaje() {
        return porcentaje;
    }
    
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public String getCursoId() {
        return cursoId;
    }
    
    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}