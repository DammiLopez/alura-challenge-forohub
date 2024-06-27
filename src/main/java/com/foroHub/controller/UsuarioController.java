package com.foroHub.controller;

import com.foroHub.domain.usario.DatosActualizarUsername;
import com.foroHub.domain.usario.DatosListaUsuario;
import com.foroHub.domain.usario.DatosLoginUsuario;
import com.foroHub.domain.usario.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> obtenerTodosLosUsuarios(@PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios(pagination));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizarNombreUsuario(@RequestBody @Valid DatosActualizarUsername datos) {
        usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<String> eliminarUsuario() {
        usuarioService.eliminarUsuario();
        return ResponseEntity.ok("Usuario eliminado");
    }
}
