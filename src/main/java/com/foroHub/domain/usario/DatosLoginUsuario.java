package com.foroHub.domain.usario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosLoginUsuario(
        @NotBlank(message = "Username vacio")
        String username,
        @NotBlank(message = "Password vacio")
        @Pattern(regexp = "\\d{4,8}", message = "Password invalido")
        String password
) {
}
