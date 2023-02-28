package com.jota.splitsmart.controller;

import com.jota.splitsmart.service.authservice.AuthService;
import com.jota.splitsmart.service.authservice.request.LoginRequestDTO;
import com.jota.splitsmart.service.authservice.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDTO login(@RequestBody final LoginRequestDTO loginRequestDTO) {
        return authService.login(loginRequestDTO);
    }
}
