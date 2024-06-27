package com.foroHub.controller;

import com.foroHub.domain.respuesta.DataRegistrarRespuesta;
import com.foroHub.domain.respuesta.DatosRespuestaCreada;
import com.foroHub.domain.respuesta.RespuestaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping("/{id}/respuesta")
    @Transactional
    public ResponseEntity<DatosRespuestaCreada> crearRespuesta(@PathVariable Integer id, @RequestBody @Valid DataRegistrarRespuesta mensaje) {
        return ResponseEntity.ok(respuestaService.crearRespuesta(id, mensaje));
    }

    @DeleteMapping("/{idTopico}/respuesta/{id}")
    @Transactional
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Integer id, @PathVariable Integer idTopico) {
        respuestaService.eliminarRespuesta(id, idTopico);
        return ResponseEntity.ok("Respuesta eliminada");
    }


}
