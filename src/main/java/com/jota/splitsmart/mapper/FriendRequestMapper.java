package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.FriendRequest;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.exchangedata.friendrequest.FriendRequestDTO;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class FriendRequestMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
    })
    public abstract FriendRequest mapToFriendRequest(final User user, final User friend);

    @Mappings({
        @Mapping(source = "friendRequest.user.name", target = "name"),
        @Mapping(source = "friendRequest.user.email", target = "email"),
        @Mapping(source = "friendRequest.id", target = "friendRequestId"),
    })
    public abstract FriendRequestDTO mapToFriendRequestDTO(final FriendRequest friendRequest);
}
