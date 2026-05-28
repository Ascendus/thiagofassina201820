package br.gov.mt.seplag.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expiration;
    private final long refreshExpiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration,
            @Value("${jwt.refresh-expiration}") long refreshExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
    }

    private String gerarToken(String username, long tempo) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tempo))
                .signWith(secretKey)
                .compact();
    }

    public String gerarAccessToken(String username) {
        return gerarToken(username, expiration);
    }

    public String gerarRefreshToken(String username) {
        return gerarToken(username, refreshExpiration);
    }

    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extrairUsername(String token) {
        return extrairClaims(token).getSubject();
    }

    public boolean isTokenValido(String token, String username) {
        return extrairUsername(token).equals(username) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

}