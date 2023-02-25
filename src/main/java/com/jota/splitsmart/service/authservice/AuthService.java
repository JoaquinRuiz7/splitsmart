package com.jota.splitsmart.service.authservice;

import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.authservice.request.LoginRequestDTO;
import com.jota.splitsmart.service.authservice.response.AuthResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public AuthResponseDTO login(final LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("User with email %s not found", loginRequestDTO.getEmail()));
        });

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getId());
        Instant now = Instant.now();
        Instant expirationTime = now.plus(1, ChronoUnit.DAYS);
        claims.put("iat", Date.from(now));
        claims.put("exp", Date.from(expirationTime));
        final String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
        return null;

    }
}
