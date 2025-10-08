package Veterinaria;

import java.util.ArrayList;
import java.util.List;

public class mascota {
    private String nombre;
    private String especie;
    private int edad;
    private dueño dueño;
    private List<ControlVeterinario> controles;

    public mascota(String nombre, String especie, int edad, dueño dueño) {
        this.nombre = nombre;
        this.especie = especie;
        setEdad(edad); // Valida edad
        this.dueño = dueño;
        this.controles = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEdad(int edad) {
        try {
            if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa");
            this.edad = edad;
        } catch (IllegalArgumentException e) {
            this.edad = 0;
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setDueño(dueño dueño) {
        this.dueño = dueño;
    }

    public dueño getDueño() {
        return dueño;
    }

    public void agregarControl(ControlVeterinario control) {
        controles.add(control);
    }

    public List<ControlVeterinario> getControles() {
        return controles;
    }

    public String getHistorialControles() {
        if (controles.isEmpty()) return "Sin controles registrados";
        StringBuilder sb = new StringBuilder();
        for (ControlVeterinario c : controles) {
            sb.append(c.registrarControlParaMascota()).append("\n");
        }
        return sb.toString();
    }

    public String registrarMascota() {
        return nombre + " " + especie + " " + edad + " " + dueño.registrarDueño();
    }
}