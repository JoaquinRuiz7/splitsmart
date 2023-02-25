package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.model.UserFriend;
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
}
