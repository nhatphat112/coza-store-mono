package com.apigateway.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {
    public String createSecretKey(){
       // create secretKey
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secretKey = Encoders.BASE64.encode(key.getEncoded());
        return secretKey;
    }
}
