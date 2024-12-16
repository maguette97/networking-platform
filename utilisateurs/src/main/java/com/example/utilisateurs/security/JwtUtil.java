package com.example.utilisateurs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

import com.example.utilisateurs.model.Utilisateur;

public class JwtUtil {

    private static final long EXPIRATION_TIME = 86400000; // 24 heures en millisecondes

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ... autres méthodes

    public static String generateToken(Utilisateur utilisateur) {
        String token = Jwts.builder()
            .setSubject(utilisateur.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SECRET_KEY)
            .compact();
        return token;
    }

    public static String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
