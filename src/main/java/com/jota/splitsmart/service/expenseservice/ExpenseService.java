package com.jota.splitsmart.service.expenseservice;

import com.jota.splitsmart.mapper.ExpenseMapper;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseDTO register(final ExpenseDTO request) {
        final Expense expense = expenseMapper.mapToExpense(request);
        expenseRepository.save(expense);
        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }

}
