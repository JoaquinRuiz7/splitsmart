package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.Debts;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.service.debtsservice.response.DebtDTO;
import java.math.BigDecimal;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class DebtsMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(constant = "false", target = "isPayed"),
        @Mapping(expression = "java(Instant.now())", target = "createdAt"),
        @Mapping(expression = "java(Instant.now())", target = "updatedAt"),
        @Mapping(source = "expense", target = "expense"),
        @Mapping(source = "user", target = "user")
    })
    public abstract Debts mapToDebt(final User user, final Expense expense,
        final BigDecimal amount);

    @Mappings({
        @Mapping(source = "debt.expense.description", target = "description"),
        @Mapping(source = "debt.expense.user.name", target = "payTo")
    })
    public abstract DebtDTO mapToDebtDTO(final Debts debt);

}
