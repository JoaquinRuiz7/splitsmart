package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.UserPaysExpense;
import java.math.BigDecimal;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class UserPaysExpenseMapper {

    @Mappings({
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
        @Mapping(constant = "false", target = "isPayed"),
    })
    public abstract UserPaysExpense mapToUserPaysExpense(final Long userId, final Long expenseId,
        final BigDecimal amount);
}
