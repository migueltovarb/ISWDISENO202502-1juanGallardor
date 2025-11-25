package com.universidad.sge.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "sesiones")
public class Sesion {

    @Id
    private String id;
    private String usuarioId;
    private String token;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaExpiracion;
    private Boolean activa;
    private String ip;
    private Integer intentosFallidos;

    public Sesion() {
        this.fechaInicio = LocalDateTime.now();
        this.fechaExpiracion = LocalDateTime.now().plusMinutes(60);
        this.activa = true;
        this.intentosFallidos = 0;
    }

    public boolean validarToken(String token) {
        if (this.token == null || token == null) {
            return false;
        }
        if (!this.activa) {
            return false;
        }
        if (LocalDateTime.now().isAfter(this.fechaExpiracion)) {
            this.activa = false;
            return false;
        }
        return this.token.equals(token);
    }

    public void renovar() {
        this.fechaExpiracion = LocalDateTime.now().plusMinutes(60);
    }

    public void cerrar() {
        this.activa = false;
    }

    public void incrementarIntentosFallidos() {
        if (this.intentosFallidos == null) {
            this.intentosFallidos = 0;
        }
        this.intentosFallidos++;
        if (this.intentosFallidos >= 5) {
            this.activa = false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
}
