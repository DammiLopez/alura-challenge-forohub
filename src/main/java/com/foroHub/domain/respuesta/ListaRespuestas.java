package com.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record ListaRespuestas(
        Integer id,
        String mensaje,
        String autor,
        LocalDateTime fechaCreacion
) {
    public ListaRespuestas(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getUsername(),
                respuesta.getFechaCreacion()
        );
    }
}
