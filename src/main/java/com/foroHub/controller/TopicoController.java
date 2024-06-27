package com.foroHub.controller;

import com.foroHub.domain.topico.DataActualizarTopico;
import com.foroHub.domain.topico.DataCrearTopico;
import com.foroHub.domain.topico.DataRespuestaTopico;
import com.foroHub.domain.topico.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public Page<DataRespuestaTopico> obtenerTodosLosTopicos(@PageableDefault(size = 10) Pageable pagination) {
        return topicoService.obtenerTodosLosTopicos(pagination).map(DataRespuestaTopico::new);
    }

    @GetMapping("/por-fecha")
    public Page<?> listarTopicosPorFecha(@PageableDefault(size = 10) Pageable pagination) {
        return topicoService.obtenerTodosLosTopicosPorFecha(pagination).map(DataRespuestaTopico::new);
    }
    @GetMapping("/{nombre}/{anio}")
    public Page listarTopicosPorNombreYAnio(
            @PageableDefault(size = 10) Pageable pagination,
            @PathVariable String nombre,
            @PathVariable int anio) {
        return topicoService.obtenerTodosLosTopicosPorNombreYAnio(pagination, nombre, anio).map(DataRespuestaTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRespuestaTopico> obtenerTopico(@PathVariable Integer id) {
        return ResponseEntity.ok(topicoService.obtenerTopicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<DataRespuestaTopico> crearTopico(
            @RequestBody @Valid DataCrearTopico dataCrear,
            UriComponentsBuilder uriComponentsBuilder) {
        DataRespuestaTopico dataRespuestaTopico = topicoService.crearTopico(dataCrear);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(dataRespuestaTopico.id()).toUri();
        return ResponseEntity.created(uri).body(dataRespuestaTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataRespuestaTopico> actualizarTopico(@PathVariable Integer id, @RequestBody @Valid DataActualizarTopico datos) {
        DataRespuestaTopico dataRespuestaTopico = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(dataRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Integer id) {
        topicoService.eliminarTopicos(id);
        return ResponseEntity.ok("Topico eliminado");
    }



}
