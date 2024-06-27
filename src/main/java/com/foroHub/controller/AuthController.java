package com.foroHub.controller;

import com.foroHub.domain.usario.*;
import com.foroHub.infrasctructure.jwt.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaUsuario> login(@RequestBody @Valid DatosLoginUsuario datosLogin){
        DatosRespuestaUsuario response = new DatosRespuestaUsuario(usuarioService.autenticarUsuario(datosLogin));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaUsuario> register(@RequestBody @Valid DataRegistrarUsuario usuarioDTO){
        DatosRespuestaUsuario response = new DatosRespuestaUsuario(usuarioService.registrarUsuario(usuarioDTO));
        return ResponseEntity.status(201).body(response);
    }

}
