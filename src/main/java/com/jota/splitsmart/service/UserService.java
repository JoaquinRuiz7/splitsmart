package com.jota.splitsmart.service;

import static java.lang.String.format;

import com.jota.splitsmart.exception.UserAlreadyRegisteredException;
import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.exchangedata.user.RegisterUserRequest;
import com.jota.splitsmart.exchangedata.user.RegisterUserResponse;
import com.jota.splitsmart.mapper.UserMapper;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserResponse register(final RegisterUserRequest request) {
        checkIfMailIsAssignable(request);
        final User user = userMapper.mapToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }

    public RegisterUserResponse update(final Long userId, final RegisterUserRequest request) {
        checkIfMailIsAssignable(request);
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(format("User with id %s not found", userId));
        });
        user = setUserInfo(user, request);
        userRepository.save(user);

        return userMapper.mapToUserDTO(user);
    }

    private User setUserInfo(final User user, final RegisterUserRequest request) {
        user.setEmail(request.getEmail() != null ? request.getEmail() : user.getEmail());
        user.setPassword(
            request.getPassword() != null ? passwordEncoder.encode(request.getPassword()) : user.getPassword());
        user.setCellphone(request.getCellphone() != null ? request.getCellphone() : user.getCellphone());
        user.setName(request.getName() != null ? request.getName() : user.getName());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    private void checkIfMailIsAssignable(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyRegisteredException(format("User with email %s already exists.", request.getEmail()));
        }
    }


}
