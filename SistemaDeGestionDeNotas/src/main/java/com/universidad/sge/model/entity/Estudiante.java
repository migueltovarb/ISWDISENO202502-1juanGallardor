package com.universidad.sge.model.entity;

import com.universidad.sge.model.enums.ClasificacionEnum;
import com.universidad.sge.model.enums.RolEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "usuarios")
public class Estudiante extends Usuario {
    
    private List<String> cursosInscritos;
    private Double promedioGeneral;
    private ClasificacionEnum clasificacion;
    
    public Estudiante() {
        super();
        this.cursosInscritos = new ArrayList<>();
        this.clasificacion = ClasificacionEnum.SIN_CALIFICAR;
    }
    
    public Estudiante(String nombre, String apellido, String email, String username, String password) {
        super(nombre, apellido, email, username, password, RolEnum.ESTUDIANTE);
        this.cursosInscritos = new ArrayList<>();
        this.clasificacion = ClasificacionEnum.SIN_CALIFICAR;
    }
    
    @Override
    public List<String> getPermisosEspecificos() {
        return Arrays.asList(
            "CONSULTAR_NOTAS_POR_CURSO",
            "CALCULAR_PROMEDIO_CURSO",
            "CALCULAR_PROMEDIO_GENERAL"
        );
    }

    public void actualizarClasificacion() {
        if (this.promedioGeneral == null) {
            this.clasificacion = ClasificacionEnum.SIN_CALIFICAR;
            return;
        }
        if (this.promedioGeneral >= 0.0 && this.promedioGeneral < 3.0) {
            this.clasificacion = ClasificacionEnum.BAJO;
        } else if (this.promedioGeneral >= 3.0 && this.promedioGeneral < 4.0) {
            this.clasificacion = ClasificacionEnum.MEDIO;
        } else if (this.promedioGeneral >= 4.0 && this.promedioGeneral < 4.6) {
            this.clasificacion = ClasificacionEnum.ALTO;
        } else if (this.promedioGeneral >= 4.6 && this.promedioGeneral <= 5.0) {
            this.clasificacion = ClasificacionEnum.EXCELENTE;
        }
    }
    
    public void inscribirCurso(String cursoId) {
        this.cursosInscritos.add(cursoId);
    }
    
    public List<String> getCursosInscritos() {
        return cursosInscritos;
    }
    
    public void setCursosInscritos(List<String> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
    
    public Double getPromedioGeneral() {
        return promedioGeneral;
    }
    
    public void setPromedioGeneral(Double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }
    
    public ClasificacionEnum getClasificacion() {
        return clasificacion;
    }
    
    public void setClasificacion(ClasificacionEnum clasificacion) {
        this.clasificacion = clasificacion;
    }
}