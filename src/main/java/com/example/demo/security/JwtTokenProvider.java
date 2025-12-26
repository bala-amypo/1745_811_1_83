package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret;
    private final long validity;

    public JwtTokenProvider(String secret, long validity) {
        this.secret = secret;
        this.validity = validity;
    }

    public String createToken(String email, String role, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("role", role)
                .claim("userId", userId)
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
