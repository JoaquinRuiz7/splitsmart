package com.jota.splitsmart.security;

import com.jota.splitsmart.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWT {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateJwtToken(final User user) {
        Date expirationDate = new Date((new Date()).getTime() + EXPIRATION_TIME);

        return Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public Long getUserIdFromToken(final String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

        return Long.valueOf(claims.getSubject());
    }

    public boolean validateJwtToken(final String authToken) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT expired ", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsopprted JWT ", e);
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT", e);
        } catch (SignatureException e) {
            log.error("Signature exception", e);
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument exception", e);
        }

        return false;

    }


}
