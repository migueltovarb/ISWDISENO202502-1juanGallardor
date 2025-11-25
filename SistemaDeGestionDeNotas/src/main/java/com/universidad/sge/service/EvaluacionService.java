package com.universidad.sge.service;

import com.universidad.sge.dto.EvaluacionRequest;
import com.universidad.sge.model.entity.Evaluacion;
import com.universidad.sge.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public Evaluacion crear(EvaluacionRequest request) {
        List<Evaluacion> evaluaciones = evaluacionRepository.findByCursoId(request.getCursoId());

        double totalPorcentaje = 0.0;
        for (Evaluacion e : evaluaciones) {
            totalPorcentaje += e.getPorcentaje();
        }

        if (totalPorcentaje + request.getPorcentaje() > 100.0) {
            throw new RuntimeException("El porcentaje total excede el 100%");
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre(request.getNombre());
        evaluacion.setPorcentaje(request.getPorcentaje());
        evaluacion.setDescripcion(request.getDescripcion());
        evaluacion.setFecha(request.getFecha());
        evaluacion.setCursoId(request.getCursoId());

        if (!evaluacion.validarPorcentaje()) {
            throw new RuntimeException("Porcentaje inválido");
        }

        if (!evaluacion.validarNombre()) {
            throw new RuntimeException("Nombre inválido");
        }

        return evaluacionRepository.save(evaluacion);
    }

    public List<Evaluacion> listarPorCurso(String cursoId) {
        return evaluacionRepository.findByCursoId(cursoId);
    }

    public Evaluacion obtenerPorId(String id) {
        return evaluacionRepository.findById(id).orElse(null);
    }
}
