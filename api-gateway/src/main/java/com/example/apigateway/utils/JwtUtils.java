package com.example.apigateway.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String SECRET_KEY = "ca91fe6ef2ec72c50e3cbc855c79226540a4c53722a67a9ebc7e58195a9e40ae";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateJwt(String authToken) {
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(authToken);
    }

}
