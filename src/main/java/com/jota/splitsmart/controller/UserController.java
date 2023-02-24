package com.jota.splitsmart.controller;

import com.jota.splitsmart.service.userservice.UserService;
import com.jota.splitsmart.service.userservice.request.RegisterUserRequest;
import com.jota.splitsmart.service.userservice.response.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserResponse register(@RequestBody final RegisterUserRequest userDTO) {
        return userService.register(userDTO);
    }
}
