package com.jota.splitsmart.service.expenseservice;

import com.jota.splitsmart.mapper.ExpenseMapper;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseDTO register(final ExpenseDTO request) {
        final Expense expense = expenseMapper.mapToExpense(request);
        expense.setUserId(request.getUserId());
        expense.setTotal(request.getTotal());
        expense.setCreatedAt(Instant.now());
        expense.setUpdatedAt(Instant.now());
        expenseRepository.save(expense);
        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }

}
