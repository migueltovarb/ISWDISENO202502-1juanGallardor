package com.universidad.sge.controller;

import com.universidad.sge.dto.NotaRequest;
import com.universidad.sge.model.entity.Nota;
import com.universidad.sge.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*")
public class NotaController {
    
    @Autowired
    private NotaService notaService;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarNota(@RequestBody NotaRequest request) {
        try {
            Nota nota = notaService.registrarNota(
                request.getValor(),
                request.getEstudianteId(),
                request.getEvaluacionId(),
                request.getCursoId(),
                request.getObservaciones(),
                request.getProfesorId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/consultar/{estudianteId}")
    public ResponseEntity<List<Nota>> consultarNotas(@PathVariable String estudianteId) {
        return ResponseEntity.ok(notaService.consultarNotas(estudianteId));
    }
}