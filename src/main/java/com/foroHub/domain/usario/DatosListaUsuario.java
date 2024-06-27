package com.foroHub.domain.usario;

public record DatosListaUsuario (
        Integer id,
        String username
) {
    public DatosListaUsuario (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getUsername()
        );
    }
}
