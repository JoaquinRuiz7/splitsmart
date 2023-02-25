package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.FriendRequest;
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
    public abstract FriendRequest mapToFriendRequest(final Long userId, final Long friendId);
}
