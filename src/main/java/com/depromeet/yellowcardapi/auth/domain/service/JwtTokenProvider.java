package com.depromeet.yellowcardapi.auth.domain.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String secret = "secret";

    // 한시간
    private static final long EXPIRATION_IN_MILLISECONDS = 3600000L;

    public String generateToken() {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + EXPIRATION_IN_MILLISECONDS);

        return Jwts.builder()
                .setSubject("subject")
                .setIssuedAt(new Date())
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
