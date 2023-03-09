package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.model.UserFriend;
import com.jota.splitsmart.exchangedata.userfriend.FriendResponseDTO;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class UserFriendMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "user", target = "user"),
        @Mapping(source = "friend", target = "friend"),
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
    })
    public abstract UserFriend mapToUserFriend(final User user, final User friend);

    @Mappings({
        @Mapping(source = "userFriend.friend.name", target = "name"),
        @Mapping(source = "userFriend.friend.email", target = "email"),
        @Mapping(source = "userFriend.friend.cellphone", target = "cellphone"),
        @Mapping(source = "userFriend.friend.id", target = "id")
    })
    public abstract FriendResponseDTO mapToUserFriendDTO(final UserFriend userFriend);
}
