package com.foroHub.infrasctructure.security;

import com.foroHub.infrasctructure.jwt.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  TokenService tokenService;
    @Autowired
    private AuthenticationService authenticationService;
    

    @Autowired
    public JwtFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Validar que sea un HEADER Authorization válido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }

        //2. Validar que sea token valido
        String jtw = authHeader.replace("Bearer ", "");
        if (!tokenService.isValid(jtw)){
            filterChain.doFilter(request, response);
            return;
        }

        //3. Cargar el usuario del UserDetailService
        String username = tokenService.getUsername(jtw);
        UserDetails userDetails = authenticationService.loadUserByUsername(username);

        //4. Cargar el usuario en el contexto de seguridad
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        authenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //5. Ejecutar el filtro
        filterChain.doFilter(request, response);

    }
}
