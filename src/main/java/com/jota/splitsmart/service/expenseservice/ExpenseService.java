package com.jota.splitsmart.service.expenseservice;

import static java.lang.String.format;
import static java.math.RoundingMode.HALF_UP;

import com.jota.splitsmart.exception.ExpenseNotFoundException;
import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.mapper.DebtsMapper;
import com.jota.splitsmart.mapper.ExpenseMapper;
import com.jota.splitsmart.persistence.model.Debts;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.repository.DebtsRepository;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import com.jota.splitsmart.service.expenseservice.request.UpdateExpenseRequest;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final DebtsRepository debtsRepository;
    private final ExpenseMapper expenseMapper;
    private final DebtsMapper userPaysExpenseMapper;
    private final UserRepository userRepository;

    private static final int EXPENSE_PAYER_USER = 1;

    @Transactional(transactionManager = "splitSmartTransactionManager")
    public ExpenseDTO register(final Long userId, final ExpenseDTO request) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(format("User with id %s not found", request.getUserId())));

        List<User> payers = userRepository.findMultipleById(request.getPayers());

        if (payers.isEmpty()) {
            throw new UserNotFoundException(format("Users with ids %s not found", request.getPayers()));
        }

        final Expense expense = expenseMapper.mapToExpense(request, user);
        expenseRepository.save(expense);

        final BigDecimal amountPerPayer = getAmountPerUser(request.getTotal(), payers.size());

        payers.forEach(payer -> {
            final Debts userDebt = userPaysExpenseMapper.mapToDebt(payer, expense, amountPerPayer);
            debtsRepository.save(userDebt);
        });

        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }

    private BigDecimal getAmountPerUser(final BigDecimal total, final int payers) {
        return total.divide(BigDecimal.valueOf(payers + EXPENSE_PAYER_USER), HALF_UP)
            .setScale(2, HALF_UP);
    }

    public ExpenseDTO updateExpense(final Long expenseId,
        final UpdateExpenseRequest updateExpenseRequest) {
        Expense expense = expenseRepository.findById(expenseId)
            .orElseThrow(() -> new ExpenseNotFoundException(format("Exception with id %s not found.", expenseId)));

        expense.setDescription(updateExpenseRequest.getDescription());
        expense.setTotal(updateExpenseRequest.getTotal());
        expenseRepository.save(expense);

        log.info("Updated expense with id {}", expenseId);

        updateAmountPerUser(expenseId, expense.getTotal());
        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }


    private void updateAmountPerUser(final Long expenseId, BigDecimal total) {
        final List<Debts> payers = debtsRepository.findAllByExpenseId(
            expenseId);
        final BigDecimal amount = getAmountPerUser(total, payers.size());

        payers.forEach(userPaysExpense -> {
            userPaysExpense.setAmount(amount);
            debtsRepository.save(userPaysExpense);
            log.info("Updated user pays expense with id {} new amount $ {}",
                userPaysExpense.getId(), amount);
        });
    }

}
