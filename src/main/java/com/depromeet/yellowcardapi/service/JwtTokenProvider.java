package com.depromeet.yellowcardapi.service;

import com.depromeet.yellowcardapi.exception.InvalidTokenException;
import com.depromeet.yellowcardapi.domain.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtTokenProvider {

    public static final String KEY_TOKEN = "Authorization";
    public static final String PREFIX_TOKEN = "Bearer";

    // an hour
    private static final long EXPIRATION_IN_MILLISECONDS = 3600000L;

    private static final Pattern PREFIX_BEARER_PATTERN =
            Pattern.compile("^" + PREFIX_TOKEN + " *([^ ]+) *$", Pattern.CASE_INSENSITIVE);

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + EXPIRATION_IN_MILLISECONDS);

        return Jwts.builder()
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(null)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) throws InvalidTokenException {
        String header = request.getHeader(KEY_TOKEN);

        if (StringUtils.isBlank(header)) {
            throw new InvalidTokenException();
        }

        Matcher matcher = PREFIX_BEARER_PATTERN.matcher(header);
        if (!matcher.matches()) {
            throw new InvalidTokenException();
        }

        return matcher.group(1);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);
        validateToken(token);

        return new UsernamePasswordAuthenticationToken(getUserId(token), "", Collections.emptyList());
    }

    public Long getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }
}
