package com.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCrearTopico(
        @NotNull(message = "id vacio")
        Integer id,
        @NotBlank(message = "Titulo vacio")
        String mensaje,
        @NotBlank(message = "Nombre del curso vacio")
        String nombreCurso,
        @NotBlank(message = "Titulo vacio")
        String titulo

) {
}
