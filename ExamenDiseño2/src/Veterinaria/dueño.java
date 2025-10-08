package Veterinaria;

import java.util.ArrayList;
import java.util.List;

public class dueño {
    private String nombreCompleto;
    private String documento;
    private String telefono;
    private List<mascota> mascotas;

    public dueño(String nombreCompleto, String documento, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.telefono = telefono;
        this.mascotas = new ArrayList<>();
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void agregarMascota(mascota m) {
        mascotas.add(m);
    }

    public List<mascota> getMascotas() {
        return mascotas;
    }

    public String registrarDueño() {
        return nombreCompleto + " " + documento + " " + telefono;
    }

    public String consultarHistorialMascota(String nombreMascota) {
        for (mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombreMascota)) {
                return m.getHistorialControles();
            }
        }
        return "Mascota no encontrada";
    }
}