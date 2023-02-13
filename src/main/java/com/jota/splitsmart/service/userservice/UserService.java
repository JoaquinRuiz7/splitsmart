package com.jota.splitsmart.service.userservice;

import com.jota.splitsmart.exception.UserAlreadyRegisteredException;
import com.jota.splitsmart.mapper.UserMapper;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.userservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDTO register(final UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyRegisteredException(
                String.format("User with email %s already exists.", userDTO.getEmail()));
        }

        final User user = userMapper.mapToUser(userDTO);
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }
}
