package com.jota.splitsmart.service.friendrequestservice;

import static java.lang.String.format;

import com.jota.splitsmart.exception.FriendAlreadyAddedException;
import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.mapper.FriendRequestMapper;
import com.jota.splitsmart.mapper.UserFriendMapper;
import com.jota.splitsmart.persistence.model.FriendRequest;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.model.UserFriend;
import com.jota.splitsmart.persistence.repository.FriendRequestRepository;
import com.jota.splitsmart.persistence.repository.UserFriendRepository;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.friendrequestservice.request.AcceptRejectFriendRequestDTO;
import com.jota.splitsmart.service.friendrequestservice.request.AddFriendRequestDTO;
import com.jota.splitsmart.service.friendrequestservice.response.FriendRequestDTO;
import java.util.ArrayList;
import java.util.List;
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
    private final UserFriendMapper userFriendMapper;
    private final UserFriendRepository userFriendRepository;

    public void sendFriendRequest(final Long userId, final AddFriendRequestDTO addFriendRequestDTO) {

        final String email = addFriendRequestDTO.getEmail();
        final String cellphone = addFriendRequestDTO.getCellphone();

        final User friend = userRepository.findByEmailOrCellphone(email, cellphone)
            .orElseThrow(() -> new UserNotFoundException(
                format("User with email  %s and cellphone %s not found.", email, cellphone)));

        if (userFriendRepository.existsByUserIdAndFriendId(userId, friend.getId())) {
            throw new FriendAlreadyAddedException("Contact already in your friend list");
        }

        final User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException(format("User with id  %s not found.", userId));
        });

        final FriendRequest friendRequest = friendRequestMapper.mapToFriendRequest(user, friend);
        friendRequestRepository.save(friendRequest);

        log.info("Sent friend request to {}", user.getEmail());

    }

    public void acceptFriendRequest(final Long friendRequestId,
        final AcceptRejectFriendRequestDTO acceptRejectFriendRequestDTO) {
        friendRequestRepository.findById(friendRequestId).ifPresent(friendRequest -> {
            if (acceptRejectFriendRequestDTO.getIsAccepted()) {
                final UserFriend userFriend = userFriendMapper.mapToUserFriend(friendRequest.getUser(),
                    friendRequest.getFriend());
                userFriendRepository.save(userFriend);
                log.info("Created user friend association for ids {} -> {}", friendRequest.getUser().getId(),
                    friendRequest.getFriend().getId());
            }
            friendRequestRepository.deleteById(friendRequest.getId());
        });
    }

    public List<FriendRequestDTO> getFriendRequests(final Long userId) {
        List<FriendRequestDTO> friendRequestDTOS = new ArrayList<>();
        friendRequestRepository.getReceivedFriendRequests(userId).forEach(friendRequest -> {
            FriendRequestDTO friendRequestDTO = friendRequestMapper.mapToFriendRequestDTO(friendRequest);
            friendRequestDTOS.add(friendRequestDTO);
        });
        return friendRequestDTOS;
    }


}
