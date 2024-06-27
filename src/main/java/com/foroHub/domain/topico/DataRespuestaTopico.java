package com.foroHub.domain.topico;

import com.foroHub.domain.respuesta.ListaRespuestas;
import com.foroHub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DataRespuestaTopico(
        Integer id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        List<ListaRespuestas> repuestas

) {
    public DataRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getRespuestas().stream().map(ListaRespuestas::new).toList()
        );

    }
}
