package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.exchangedata.user.RegisterUserRequest;
import com.jota.splitsmart.exchangedata.user.RegisterUserResponse;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class UserMapper {

    @Mappings({
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
    })
    public abstract User mapToUser(final RegisterUserRequest userDTO);

    public abstract RegisterUserResponse mapToUserDTO(final User user);

}
