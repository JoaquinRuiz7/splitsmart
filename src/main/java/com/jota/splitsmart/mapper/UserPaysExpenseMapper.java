package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.model.UserPaysExpense;
import java.math.BigDecimal;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class UserPaysExpenseMapper {

     @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(constant = "false", target = "isPayed"),
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
    })
    public abstract UserPaysExpense mapToUserPaysExpense(final User user, final Expense expense,
        final BigDecimal amount);
        
}
