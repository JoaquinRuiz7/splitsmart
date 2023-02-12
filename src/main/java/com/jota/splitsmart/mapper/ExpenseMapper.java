package com.jota.splitsmart.mapper;

import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import java.time.Instant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = Instant.class)
public abstract class ExpenseMapper {

    public abstract Expense mapToExpense(final ExpenseDTO expenseDTO);

    public abstract ExpenseDTO mapToRegisterExpenseResponse(final Expense expense);
}
