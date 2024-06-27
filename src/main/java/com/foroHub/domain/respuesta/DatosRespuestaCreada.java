package com.foroHub.domain.respuesta;

import com.foroHub.domain.usario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaCreada (
    Integer id,
    LocalDateTime fechaCreacion,
    String autor,
    String topico

){
}
