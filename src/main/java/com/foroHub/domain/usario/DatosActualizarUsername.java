package com.foroHub.domain.usario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsername(
        @NotBlank
        String newUsername
        ) {
}
