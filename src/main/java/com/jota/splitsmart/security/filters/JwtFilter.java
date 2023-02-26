package com.jota.splitsmart.security.filters;

import com.jota.splitsmart.exception.InvalidTokenException;
import com.jota.splitsmart.exception.NoTokenException;
import com.jota.splitsmart.security.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JWT jwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);

            if (token == null) {
                throw new NoTokenException("No auth token sent in request.");
            }

            if (!jwt.validateJwtToken(token)) {
                throw new InvalidTokenException("The token is invalid.");
            }
            
        } catch (Exception ex) {
            // Handle exceptions here
            SecurityContextHolder.clearContext();
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(final HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

}
