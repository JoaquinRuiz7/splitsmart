package com.jota.splitsmart.service.userservice;

import static java.lang.String.format;

import com.jota.splitsmart.exception.UserAlreadyRegisteredException;
import com.jota.splitsmart.mapper.UserMapper;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.userservice.request.RegisterUserRequest;
import com.jota.splitsmart.service.userservice.response.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserResponse register(final RegisterUserRequest userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyRegisteredException(format("User with email %s already exists.", userDTO.getEmail()));
        }

        final User user = userMapper.mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }
}
