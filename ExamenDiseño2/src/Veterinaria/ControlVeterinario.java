package Veterinaria;

import java.util.Date;

public class ControlVeterinario {
    public enum TipoControl {
        VACUNA, CHEQUEO, DESPARACITACION
    }

    private Date fecha;
    private String observacion;
    private TipoControl tipoControl;

    public ControlVeterinario(Date fecha, String observacion, TipoControl tipoControl) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.tipoControl = tipoControl;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setTipoControl(TipoControl tipoControl) {
        this.tipoControl = tipoControl;
    }

    public TipoControl getTipoControl() {
        return tipoControl;
    }

    public String registrarControlParaMascota() {
        return "Fecha: " + fecha + ", Observación: " + observacion + ", Tipo: " + tipoControl;
    }

    public String generarResumen() {
        return "Resumen - " + registrarControlParaMascota();
    }
}