package com.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DataActualizarTopico(
        String titulo,
        String mensaje,
        String status,
        String nombreCurso
) {

}
