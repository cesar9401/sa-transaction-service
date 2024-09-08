package com.cesar31.transaction.infrastructure.adapters.input.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${security.jwt.ttlMillis}")
    private Long TTL_MILLIS;

    @Value("${security.jwt.issuer}")
    private String ISSUER;

    public String getUsername(String token) {
        var claims = extractClaims(token);
        return claims.getSubject();
    }

    public boolean isValid(String token) {
        var claims = extractClaims(token);
        var expiration = claims.getExpiration();
        return new Date().before(expiration);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
