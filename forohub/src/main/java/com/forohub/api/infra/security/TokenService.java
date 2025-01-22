package com.forohub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forohub.api.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String ISSUER = "ForoHub API";
    private static final String ZONE_OFFSET = "-05:00";
    private static final int TOKEN_DURATION_HOURS = 2;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new SecurityException("Error al generar el token JWT", exception);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new SecurityException("Token JWT inválido o expirado");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(TOKEN_DURATION_HOURS)
                .toInstant(ZoneOffset.of(ZONE_OFFSET));
    }
}