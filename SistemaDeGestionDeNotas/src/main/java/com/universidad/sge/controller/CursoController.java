package com.universidad.sge.controller;

import com.universidad.sge.dto.CursoRequest;
import com.universidad.sge.dto.EvaluacionRequest;
import com.universidad.sge.model.entity.Curso;
import com.universidad.sge.model.entity.Evaluacion;
import com.universidad.sge.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearCurso(@RequestBody CursoRequest request) {
        try {
            Curso curso = cursoService.crearCurso(request.getNombre(), request.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }
    
    @PostMapping("/evaluaciones/crear")
    public ResponseEntity<?> crearEvaluacion(@RequestBody EvaluacionRequest request) {
        try {
            Evaluacion evaluacion = cursoService.crearEvaluacion(
                request.getNombre(),
                request.getPorcentaje(),
                request.getDescripcion(),
                request.getCursoId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/evaluaciones/curso/{cursoId}")
    public ResponseEntity<List<Evaluacion>> listarEvaluaciones(@PathVariable String cursoId) {
        return ResponseEntity.ok(cursoService.listarEvaluaciones(cursoId));
    }
}