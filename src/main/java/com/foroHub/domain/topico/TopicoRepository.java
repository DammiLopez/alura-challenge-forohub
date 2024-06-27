package com.foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    Page<Topico> findAllByOrderByIdAsc(Pageable pageable);
    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion ASC")
    Page<Topico> findAllByfechaCreacionOrderByIdAsc(Pageable pagination);

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    Page<Topico> findAllByNameAndYear(Pageable pageable, @Param("nombreCurso") String nombreCurso, @Param("anio") int anio);

}