package com.jota.splitsmart.mapper;

import com.jota.splitsmart.exchangedata.debts.ExpenseDebtDTO;
import com.jota.splitsmart.exchangedata.debts.UserDebtDTO;
import com.jota.splitsmart.persistence.model.Debts;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
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
    public abstract UserDebtDTO mapToUserDebtDTO(final Debts debt);

    @Mappings({
        @Mapping(source = "debt.expense.description", target = "description"),
        @Mapping(source = "debt.user.name", target = "userName"),
        @Mapping(source = "debt.amount", target = "amount"),
        @Mapping(source = "debt.isPayed", target = "isPayed")
    })
    public abstract ExpenseDebtDTO mapToExpenseDebtDTO(final Debts debt);

}
