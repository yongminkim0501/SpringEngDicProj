package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtProperties {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token-validity-in-milliseconds}")
    private int tokenvalidityInMilliseconds;

    private Key key;

    public Key getKey(){
        if (key == null){
            key = Keys.hmacShaKeyFor(secret.getBytes());
        }
        return key;
    }
    public long getTokenValidityInMilliseconds(){
        return tokenvalidityInMilliseconds;
    }
}
