package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class ExpenseMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
        @Mapping(source = "user", target = "user")
    })
    public abstract Expense mapToExpense(final ExpenseDTO expenseDTO, final User user);

    public abstract ExpenseDTO mapToRegisterExpenseResponse(final Expense expense);

}
