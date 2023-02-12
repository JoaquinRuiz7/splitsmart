package com.jota.splitsmart.service.userservice;

import com.jota.splitsmart.mapper.UserMapper;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.userservice.dto.UserDTO;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDTO register(final UserDTO userDTO) {
        final User user = userMapper.mapToUser(userDTO);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }
}
