package com.foroHub.domain.usario;

import com.foroHub.domain.perfil.Perfil;
import com.foroHub.domain.perfil.PerfilRepository;
import com.foroHub.infrasctructure.jwt.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public String registrarUsuario(DataRegistrarUsuario usuarioDTO) {
        Perfil perfil = perfilRepository.findById(3).get();
        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);
        return tokenService.generateToken(usuario);
    }
    public String autenticarUsuario(DatosLoginUsuario datosLogin) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datosLogin.username(),
                datosLogin.password());
        Authentication usuarioAuth = authenticationManager.authenticate(authenticationToken);
        var user = (Usuario)usuarioAuth.getPrincipal();
        if(user.getActivo() == 0){
            throw new EntityNotFoundException("El usuario ha sido eliminado");
        }
        return tokenService.generateToken((Usuario) usuarioAuth.getPrincipal());
    }

    public Page<DatosListaUsuario> obtenerTodosLosUsuarios(Pageable pagination) {
        return usuarioRepository.findAllByActivo(pagination).map(DatosListaUsuario::new);
    }

    public void actualizarUsuario(DatosActualizarUsername datos) {
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.actualizarUsername(datos.newUsername());
        usuarioRepository.save(user);
    }

    public void eliminarUsuario() {
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.borrar();
        usuarioRepository.save(user);
    }
}
