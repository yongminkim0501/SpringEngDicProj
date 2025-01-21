package com.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    public String createToken(String userEmail){
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getTokenValidityInMilliseconds());

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(jwtProperties.getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}