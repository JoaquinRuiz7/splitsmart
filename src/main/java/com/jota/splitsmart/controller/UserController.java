package com.jota.splitsmart.controller;

import com.jota.splitsmart.exchangedata.user.RegisterUserRequest;
import com.jota.splitsmart.exchangedata.user.RegisterUserResponse;
import com.jota.splitsmart.security.SecurityGuard;
import com.jota.splitsmart.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final SecurityGuard securityGuard;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserResponse register(@RequestBody @Valid final RegisterUserRequest request) {
        return userService.register(request);
    }

    @PatchMapping("/{userId}")
    public RegisterUserResponse update(@PathVariable final Long userId,
        @RequestBody @Valid final RegisterUserRequest request) {
        securityGuard.checkIfTokenBelongsToUser(userId);
        return userService.update(userId, request);
    }
}
