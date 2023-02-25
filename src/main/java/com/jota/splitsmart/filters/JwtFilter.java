package com.jota.splitsmart.filters;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(UNAUTHORIZED.value());
            return;
        }

        final String token = authorizationHeader.substring(7);

        try {
            JwtParser jwtParser = Jwts.parser().setSigningKey(jwtSecret);
            final Claims claims = jwtParser.parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        } catch (JwtException e) {
            response.setStatus(UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
