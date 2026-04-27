package com.fiap.meetingroom.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(String username) {
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(agora)
                .expiration(validade)
                .signWith(getChave())
                .compact();
    }

    public String extrairUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean tokenValido(String token) {
        try {
            extrairUsername(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}