package com.universidad.sge.model.entity;

import com.universidad.sge.model.enums.RolEnum;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Usuario {

    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private RolEnum rol;
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    public Usuario() {
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Usuario(String nombre, String apellido, String email, String username, String password, RolEnum rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public abstract List<String> getPermisosEspecificos();

    public String getNombreCompleto() {
        return apellido + " " + nombre;
    }

    public boolean validarPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    public boolean isActivo() {
        return this.activo != null && this.activo;
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
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public RolEnum getRol() {
        return rol;
    }
    
    public void setRol(RolEnum rol) {
        this.rol = rol;
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