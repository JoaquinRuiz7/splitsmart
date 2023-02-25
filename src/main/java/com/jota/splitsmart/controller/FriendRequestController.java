package com.jota.splitsmart.controller;

import static com.jota.splitsmart.context.ContextData.USER_ID;

import com.jota.splitsmart.service.friendrequestservice.FriendRequestService;
import com.jota.splitsmart.service.friendrequestservice.request.AcceptRejectFriendRequestDTO;
import com.jota.splitsmart.service.friendrequestservice.request.AddFriendRequestDTO;
import com.jota.splitsmart.service.friendrequestservice.response.FriendRequestDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{userId}/friend-requests")
    public List<FriendRequestDTO> getReceivedFriendRequests(@PathVariable final Long userId) {
        return friendRequestService.getFriendRequests(userId);
    }

    @DeleteMapping("/{friendRequestId}")
    public void manageFriendRequest(@PathVariable final Long friendRequestId,
        @RequestBody final AcceptRejectFriendRequestDTO acceptRejectFriendRequestDTO) {
        friendRequestService.acceptFriendRequest(friendRequestId, acceptRejectFriendRequestDTO);
    }
}
