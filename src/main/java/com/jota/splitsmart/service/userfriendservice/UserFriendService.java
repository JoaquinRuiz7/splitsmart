package com.jota.splitsmart.service.userfriendservice;

import com.jota.splitsmart.mapper.UserFriendMapper;
import com.jota.splitsmart.persistence.repository.UserFriendRepository;
import com.jota.splitsmart.service.userfriendservice.response.FriendResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;
    private final UserFriendMapper userFriendMapper;

    public List<FriendResponseDTO> getUserFriends(final Long userId) {
        return userFriendRepository.findAllByUserId(userId).stream().map(userFriendMapper::mapToUserFriendDTO).toList();
    }
}
