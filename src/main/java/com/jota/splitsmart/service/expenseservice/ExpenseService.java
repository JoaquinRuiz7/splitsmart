package com.jota.splitsmart.service.expenseservice;

import com.jota.splitsmart.mapper.ExpenseMapper;
import com.jota.splitsmart.mapper.UserPaysExpenseMapper;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.UserPaysExpense;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.persistence.repository.UserPaysExpenseRepository;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserPaysExpenseRepository userPaysExpenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserPaysExpenseMapper userPaysExpenseMapper;

    private static final int EXPENSE_PAYER_USER = 1;

    public ExpenseDTO register(final Long userId, final ExpenseDTO request) {
        final Expense expense = expenseMapper.mapToExpense(request, userId);
        final BigDecimal amountPerPayer = getAmmountPerUser(request.getTotal(),
            request.getPayers().size() + EXPENSE_PAYER_USER);
        expenseRepository.save(expense);

        request.getPayers().forEach(payerId -> {
            final UserPaysExpense userPaysExpense = userPaysExpenseMapper.mapToUserPaysExpense(payerId, expense.getId(),
                amountPerPayer);
            userPaysExpenseRepository.save(userPaysExpense);
        });
        
        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }

    private BigDecimal getAmmountPerUser(final BigDecimal total, final int payers) {
        return total.divide(BigDecimal.valueOf(payers)).setScale(2, RoundingMode.HALF_UP);
    }

}
