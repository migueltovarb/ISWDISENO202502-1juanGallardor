package com.universidad.sge.service;

import com.universidad.sge.model.entity.Estudiante;
import com.universidad.sge.model.entity.Evaluacion;
import com.universidad.sge.model.entity.Nota;
import com.universidad.sge.model.enums.ClasificacionEnum;
import com.universidad.sge.repository.EvaluacionRepository;
import com.universidad.sge.repository.NotaRepository;
import com.universidad.sge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotaService {
    
    @Autowired
    private NotaRepository notaRepository;
    
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Nota registrarNota(Double valor, String estudianteId, String evaluacionId, String cursoId, String observaciones, String profesorId) {
        
        if (valor < 0.0 || valor > 5.0) {
            throw new RuntimeException("La nota debe estar entre 0.0 y 5.0");
        }
        
        Evaluacion evaluacion = evaluacionRepository.findById(evaluacionId)
            .orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));
        
        Nota nota = new Nota();
        nota.setValor(valor);
        nota.setEstudianteId(estudianteId);
        nota.setEvaluacionId(evaluacionId);
        nota.setCursoId(cursoId);
        nota.setObservaciones(observaciones);
        nota.setProfesorRegistro(profesorId);
        
        nota.calcularAporte(evaluacion.getPorcentaje());
        
        nota = notaRepository.save(nota);
        
        calcularPromedioCurso(estudianteId, cursoId);
        calcularPromedioGeneral(estudianteId);
        
        return nota;
    }
    
    public List<Nota> consultarNotas(String estudianteId) {
        return notaRepository.findByEstudianteId(estudianteId);
    }
    
    private void calcularPromedioCurso(String estudianteId, String cursoId) {
        List<Nota> notas = notaRepository.findByEstudianteIdAndCursoId(estudianteId, cursoId);
        
        Double promedioTotal = 0.0;
        for (Nota nota : notas) {
            promedioTotal += nota.getAportePromedio();
        }
        
        System.out.println("Promedio calculado para estudiante " + estudianteId + " en curso " + cursoId + ": " + promedioTotal);
    }
    
    private void calcularPromedioGeneral(String estudianteId) {
        Estudiante estudiante = (Estudiante) usuarioRepository.findById(estudianteId)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        
        List<String> cursos = estudiante.getCursosInscritos();
        
        Double sumaPromedios = 0.0;
        int contadorCursos = 0;
        
        for (String cursoId : cursos) {
            List<Nota> notas = notaRepository.findByEstudianteIdAndCursoId(estudianteId, cursoId);
            
            if (!notas.isEmpty()) {
                Double promedioCurso = 0.0;
                for (Nota nota : notas) {
                    promedioCurso += nota.getAportePromedio();
                }
                
                sumaPromedios += promedioCurso;
                contadorCursos++;
            }
        }
        
        Double promedioGeneral = contadorCursos > 0 ? sumaPromedios / contadorCursos : null;
        
        estudiante.setPromedioGeneral(promedioGeneral);
        estudiante.setClasificacion(clasificarPromedio(promedioGeneral));
        
        usuarioRepository.save(estudiante);
        
        System.out.println("Promedio general actualizado: " + promedioGeneral + " - Clasificacion: " + estudiante.getClasificacion());
    }
    
    private ClasificacionEnum clasificarPromedio(Double promedio) {
        if (promedio == null) {
            return ClasificacionEnum.SIN_CALIFICAR;
        }
        
        if (promedio < 3.0) {
            return ClasificacionEnum.BAJO;
        } else if (promedio < 4.0) {
            return ClasificacionEnum.MEDIO;
        } else if (promedio <= 4.5) {
            return ClasificacionEnum.ALTO;
        } else {
            return ClasificacionEnum.EXCELENTE;
        }
    }
}