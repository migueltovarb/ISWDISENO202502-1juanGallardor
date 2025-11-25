package com.universidad.sge.dto;

public class NotaRequest {
    private Double valor;
    private String estudianteId;
    private String evaluacionId;
    private String cursoId;
    private String observaciones;
    private String profesorId;
    
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public String getEstudianteId() {
        return estudianteId;
    }
    
    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }
    
    public String getEvaluacionId() {
        return evaluacionId;
    }
    
    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }
    
    public String getCursoId() {
        return cursoId;
    }
    
    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getProfesorId() {
        return profesorId;
    }
    
    public void setProfesorId(String profesorId) {
        this.profesorId = profesorId;
    }
}