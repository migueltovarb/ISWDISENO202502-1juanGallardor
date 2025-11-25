package com.universidad.sge.repository;

import com.universidad.sge.model.entity.Evaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends MongoRepository<Evaluacion, String> {
    List<Evaluacion> findByCursoId(String cursoId);
}