package com.jota.splitsmart.controller;

import static com.jota.splitsmart.context.ContextData.USER_ID;

import com.jota.splitsmart.service.friendrequestservice.FriendRequestService;
import com.jota.splitsmart.service.friendrequestservice.request.AddFriendRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/friend-request")
@RequiredArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendFriendRequest(@RequestHeader(name = USER_ID) final Long userId, @RequestBody final
    AddFriendRequestDTO addFriendRequestDTO) {
        friendRequestService.sendFriendRequest(userId, addFriendRequestDTO);
    }
}
