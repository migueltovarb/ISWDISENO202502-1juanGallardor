package com.universidad.sge.repository;

import com.universidad.sge.model.entity.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {
    List<Nota> findByEstudianteIdAndCursoId(String estudianteId, String cursoId);
    List<Nota> findByEstudianteId(String estudianteId);
}