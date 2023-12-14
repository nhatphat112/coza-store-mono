package com.apigateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtHelper {
    @Value("${jwt.secret.key}")
    private String secretKey;
    public String createSecretKey(){
       // create secretKey
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secretKey = Encoders.BASE64.encode(key.getEncoded());
        return secretKey;

    }
    // generateToken
    public String generateToken(String data){
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        String token = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .compact();
        return  token;
    }
    public Claims decodeToken(String token){
        Claims claims = null;
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getBody(); // return claims
        }catch (Exception e){
           System.out.println(e.getMessage());

        }
        return claims;
    }
    // setSubject
    //setSignWithKey
}
