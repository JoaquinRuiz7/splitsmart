package com.jota.splitsmart.service;

import com.jota.splitsmart.exception.FriendAlreadyAddedException;
import com.jota.splitsmart.exchangedata.userfriend.FriendResponseDTO;
import com.jota.splitsmart.mapper.UserFriendMapper;
import com.jota.splitsmart.persistence.repository.UserFriendRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;
    private final UserFriendMapper userFriendMapper;

    public List<FriendResponseDTO> getUserFriends(final Long userId) {
        return userFriendRepository.findAllByUserId(userId).stream().map(userFriendMapper::mapToUserFriendDTO).toList();
    }

    @Transactional(transactionManager = "splitSmartTransactionManager")
    public void deleteFriend(final Long userId, final Long friendId) {
        if (!userFriendRepository.existsByUserIdAndFriendId(userId, friendId)) {
            throw new FriendAlreadyAddedException("Contact already unfriend or not on your friend list ");
        }
        userFriendRepository.deleteFriend(friendId);
        log.info("Deleted friend with id {}", friendId);
    }
}
