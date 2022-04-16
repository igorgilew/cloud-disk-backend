package com.storage.cloud.security;

import com.storage.cloud.domain.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final String secret = "jwt_secret";

    private final int expirationTimeMs = 2*60*1000;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTimeMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean isTokenValid(String jwt) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            logger.debug("Jwt validation fail: " + e.getMessage());
            return false;
        }
        return true;
    }

    public String getEmailFromToken(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
    }

}
