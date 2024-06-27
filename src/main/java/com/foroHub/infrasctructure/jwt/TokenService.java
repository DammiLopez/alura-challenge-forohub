package com.foroHub.infrasctructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.foroHub.domain.usario.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private final String SECRET = "mi-challenge-foro-hub";
    private Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public String generateToken(Usuario user) {
        try {
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("foroHub")
                    .withClaim("id", user.getId())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(
                            System.currentTimeMillis() + TimeUnit
                                    .DAYS.toMillis(15)
                    ))
                    .sign(ALGORITHM);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al generar el token");
        }
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

}
