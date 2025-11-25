package com.universidad.sge.repository;

import com.universidad.sge.model.entity.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    List<Curso> findByActivo(Boolean activo);
    List<Curso> findByProfesorId(String profesorId);
}