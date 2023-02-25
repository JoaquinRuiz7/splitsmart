package com.jota.splitsmart.service.userfriendservice;

import com.jota.splitsmart.persistence.repository.UserFriendRepository;
import com.jota.splitsmart.service.userfriendservice.response.FriendResponseDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;

    public List<FriendResponseDTO> getUserFriends(final Long userId) {
        List<FriendResponseDTO> friendResponseDTOS = new ArrayList<>();
        userFriendRepository.findAllByUserId(userId).forEach(userFriend -> {
            FriendResponseDTO friendResponseDTO = new FriendResponseDTO();
            //friendResponseDTO.setCellphone(userFriend.);
        });
        return null;
    }
}
