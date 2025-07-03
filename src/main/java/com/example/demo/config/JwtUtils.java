package com.example.demo.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    String jwtSecret;
    private final long accessTokenExpiresIn = 60000 * 15; //1000 * 60 *15
    private final long refreshTokenExpiresIn = 60000 * 60 * 24;

    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiresIn);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiresIn);
    }

    private String generateToken(String username, long tokenExpiresIn) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiresIn))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }

    }
}