package com.jota.splitsmart.service.friendrequestservice;

import static java.lang.String.format;

import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.mapper.FriendRequestMapper;
import com.jota.splitsmart.persistence.model.FriendRequest;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.FriendRequestRepository;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.friendrequestservice.request.AddFriendRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendRequestService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRequestMapper friendRequestMapper;

    public void sendFriendRequest(final Long userId, final AddFriendRequestDTO addFriendRequestDTO) {

        final String email = addFriendRequestDTO.getEmail();
        final String cellphone = addFriendRequestDTO.getCellphone();

        final User user = userRepository.findByEmailOrCellphone(email, cellphone)
            .orElseThrow(() -> new UserNotFoundException(
                format("User with email  %s and cellphone %s not found.", email, cellphone)));

        final FriendRequest friendRequest = friendRequestMapper.mapToFriendRequest(userId, user.getId());
        friendRequestRepository.save(friendRequest);

        log.info("Sent friend request to {}", user.getEmail());

    }


}
