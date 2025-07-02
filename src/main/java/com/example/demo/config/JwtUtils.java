package com.example.demo.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private String jwtSecret;
    private final long accessTokenExpiresIn = 60000 * 15;
    private final long refreshTokenExpiresIn = 60000 * 60 * 24;

    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiresIn);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiresIn);
    }

    private String generateToken(String username, long expiresIn) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    private String extractUsername(String token){
        return Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}