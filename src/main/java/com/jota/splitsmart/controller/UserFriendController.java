package com.jota.splitsmart.controller;

import com.jota.splitsmart.exchangedata.userfriend.FriendResponseDTO;
import com.jota.splitsmart.security.SecurityGuard;
import com.jota.splitsmart.service.UserFriendService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user-friends")
@RequiredArgsConstructor
public class UserFriendController {

    private final UserFriendService userFriendService;
    private final SecurityGuard securityGuard;

    @GetMapping("/{userId}/friends")
    public List<FriendResponseDTO> getUserFriends(@PathVariable final Long userId) {
        securityGuard.checkIfTokenBelongsToUser(userId);
        return userFriendService.getUserFriends(userId);
    }

    @DeleteMapping("/{userId}/remove/{friendId}")
    public void deleteFriend(@PathVariable final Long friendId, @PathVariable final Long userId) {
        securityGuard.checkIfTokenBelongsToUser(userId);
        userFriendService.deleteFriend(userId, friendId);
    }
}
