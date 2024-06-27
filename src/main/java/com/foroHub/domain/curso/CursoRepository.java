package com.foroHub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Optional<Curso> findByNombre(String s);
}
