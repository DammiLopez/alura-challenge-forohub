package com.foroHub.domain.usario;

import com.foroHub.domain.perfil.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegistrarUsuario(
        @NotBlank(message = "Nombre de usuario vacio")
        String username,
        @NotBlank(message = "Email vacio")
        @Email
        String email,
        @NotBlank(message = "Contrasena vacia")
        @Pattern(regexp = "\\d{4,8}", message = "Password invalido")
        String password
) {
}
