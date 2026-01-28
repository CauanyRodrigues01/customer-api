package com.example.customer_api.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.customer_api.security.config.JwtProperties;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties properties;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(properties.getSecretKey());
    }

    // GERAÇÃO DO TOKEN
    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();

        return JWT.create()
            .withSubject(authentication.getName())
            .withClaim(
                "roles",
                authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList()
            )
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(now.plusSeconds(properties.getExpirationSeconds())))
            .sign(algorithm());
    }

    // EXTRAÇÃO DE DADOS
    public String extractUsername(String token) {
        return JWT.require(algorithm())
            .build()
            .verify(token)
            .getSubject();
    }

    public List<String> extractRoles(String token) {
        return JWT.require(algorithm())
            .build()
            .verify(token)
            .getClaim("roles")
            .asList(String.class);
    }

    // VALIDAÇÃO DO TOKEN
    public boolean isTokenValid(
        String token,
        UserDetails userDetails
    ) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Instant expiration = JWT.require(algorithm())
            .build()
            .verify(token)
            .getExpiresAt()
            .toInstant();

        return expiration.isBefore(Instant.now());
    }
}
