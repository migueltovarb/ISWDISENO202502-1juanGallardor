package com.universidad.sge.repository;

import com.universidad.sge.model.entity.Sesion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SesionRepository extends MongoRepository<Sesion, String> {

    Optional<Sesion> findByToken(String token);

    Optional<Sesion> findByUsuarioIdAndActivaTrue(String usuarioId);
}
