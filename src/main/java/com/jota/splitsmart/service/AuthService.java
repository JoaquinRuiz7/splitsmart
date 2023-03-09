package com.jota.splitsmart.service;

import static java.lang.String.format;

import com.jota.splitsmart.exception.InvalidUserCredentialsException;
import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.exchangedata.auth.LoginRequestDTO;
import com.jota.splitsmart.exchangedata.auth.LoginResponseDTO;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.security.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWT jwt;

    public LoginResponseDTO login(final LoginRequestDTO loginRequestDTO) {

        final User user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> {
            throw new UserNotFoundException(format("User with email %s not found", loginRequestDTO.getEmail()));
        });

        final boolean passwordMatches = passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new InvalidUserCredentialsException("Invalid credentials");
        }

        log.info("Generating JWT for user {}", user.getId());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(jwt.generateJwtToken(user));
        loginResponseDTO.setUserId(user.getId());

        return loginResponseDTO;

    }
}
