package com.universidad.sge.controller;

import com.universidad.sge.model.entity.*;
import com.universidad.sge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    @PostMapping("/crear-datos-prueba")
    public ResponseEntity<?> crearDatosPrueba() {
        
        Administrador admin = new Administrador(
            "Carlos",
            "Rodriguez",
            "admin@universidad.edu.co",
            "admin",
            "admin123"
        );
        admin = (Administrador) usuarioRepository.save(admin);
        
        Profesor profesor = new Profesor(
            "Maria",
            "Gonzalez",
            "maria.gonzalez@universidad.edu.co",
            "maria.gonzalez",
            "profesor123"
        );
        profesor = (Profesor) usuarioRepository.save(profesor);
        
        Estudiante estudiante1 = new Estudiante(
            "Juan",
            "Perez",
            "juan.perez@universidad.edu.co",
            "juan.perez",
            "estudiante123"
        );
        estudiante1 = (Estudiante) usuarioRepository.save(estudiante1);
        
        Estudiante estudiante2 = new Estudiante(
            "Ana",
            "Martinez",
            "ana.martinez@universidad.edu.co",
            "ana.martinez",
            "estudiante456"
        );
        estudiante2 = (Estudiante) usuarioRepository.save(estudiante2);
        
        Curso curso = new Curso();
        curso.setNombre("Programacion Web Avanzada");
        curso.setDescripcion("Desarrollo de aplicaciones web con Spring Boot");
        curso.generarCodigo();
        curso.setProfesorId(profesor.getId());
        curso = cursoRepository.save(curso);
        
        profesor.agregarCurso(curso.getId());
        usuarioRepository.save(profesor);
        
        estudiante1.inscribirCurso(curso.getId());
        estudiante2.inscribirCurso(curso.getId());
        usuarioRepository.save(estudiante1);
        usuarioRepository.save(estudiante2);
        
        Evaluacion parcial1 = new Evaluacion();
        parcial1.setNombre("Parcial 1");
        parcial1.setPorcentaje(30.0);
        parcial1.setDescripcion("Primer parcial del semestre");
        parcial1.setCursoId(curso.getId());
        evaluacionRepository.save(parcial1);
        
        Evaluacion taller1 = new Evaluacion();
        taller1.setNombre("Taller 1");
        taller1.setPorcentaje(20.0);
        taller1.setDescripcion("Taller practico");
        taller1.setCursoId(curso.getId());
        evaluacionRepository.save(taller1);
        
        Evaluacion proyecto = new Evaluacion();
        proyecto.setNombre("Proyecto Final");
        proyecto.setPorcentaje(50.0);
        proyecto.setDescripcion("Proyecto final del curso");
        proyecto.setCursoId(curso.getId());
        evaluacionRepository.save(proyecto);
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Datos de prueba creados exitosamente");
        response.put("admin", Map.of("username", "admin", "password", "admin123"));
        response.put("profesor", Map.of("username", "maria.gonzalez", "password", "profesor123"));
        response.put("estudiante1", Map.of("username", "juan.perez", "password", "estudiante123"));
        response.put("estudiante2", Map.of("username", "ana.martinez", "password", "estudiante456"));
        response.put("curso", curso);
        response.put("estudianteId", estudiante1.getId());
        response.put("profesorId", profesor.getId());
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/limpiar-datos")
    public ResponseEntity<?> limpiarDatos() {
        usuarioRepository.deleteAll();
        cursoRepository.deleteAll();
        evaluacionRepository.deleteAll();
        
        return ResponseEntity.ok("Todos los datos han sido eliminados");
    }
}