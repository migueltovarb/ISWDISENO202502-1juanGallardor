package com.universidad.sge.service;

import com.universidad.sge.model.entity.Curso;
import com.universidad.sge.model.entity.Evaluacion;
import com.universidad.sge.repository.CursoRepository;
import com.universidad.sge.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    public Curso crearCurso(String nombre, String descripcion) {
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.generarCodigo();
        
        return cursoRepository.save(curso);
    }
    
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }
    
    public Evaluacion crearEvaluacion(String nombre, Double porcentaje, String descripcion, String cursoId) {
        
        List<Evaluacion> evaluacionesExistentes = evaluacionRepository.findByCursoId(cursoId);
        
        Double sumaActual = 0.0;
        for (Evaluacion eval : evaluacionesExistentes) {
            sumaActual += eval.getPorcentaje();
        }
        
        if (sumaActual + porcentaje > 100.0) {
            throw new RuntimeException("La suma de porcentajes excede 100%. Actual: " + sumaActual + "%, intentando agregar: " + porcentaje + "%");
        }
        
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre(nombre);
        evaluacion.setPorcentaje(porcentaje);
        evaluacion.setDescripcion(descripcion);
        evaluacion.setCursoId(cursoId);
        
        return evaluacionRepository.save(evaluacion);
    }
    
    public List<Evaluacion> listarEvaluaciones(String cursoId) {
        return evaluacionRepository.findByCursoId(cursoId);
    }
}