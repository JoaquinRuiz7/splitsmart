package com.jota.splitsmart.service.expenseservice;

import static java.lang.String.format;
import static java.math.RoundingMode.HALF_UP;

import com.jota.splitsmart.exception.UserNotFoundException;
import com.jota.splitsmart.mapper.ExpenseMapper;
import com.jota.splitsmart.mapper.UserPaysExpenseMapper;
import com.jota.splitsmart.persistence.model.Expense;
import com.jota.splitsmart.persistence.model.User;
import com.jota.splitsmart.persistence.model.UserPaysExpense;
import com.jota.splitsmart.persistence.repository.ExpenseRepository;
import com.jota.splitsmart.persistence.repository.UserPaysExpenseRepository;
import com.jota.splitsmart.persistence.repository.UserRepository;
import com.jota.splitsmart.service.expenseservice.dto.DebtDTO;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserPaysExpenseRepository userPaysExpenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserPaysExpenseMapper userPaysExpenseMapper;
    private final UserRepository userRepository;

    private static final int EXPENSE_PAYER_USER = 1;

    public ExpenseDTO register(final Long userId, final ExpenseDTO request) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(format("User with id %s not found", request.getUserId()));
        }

        List<User> payers = userRepository.findMultipleById(request.getPayers());

        if (payers.isEmpty()) {
            throw new UserNotFoundException(format("Users with ids %s not found", request.getPayers()));
        }

        final Expense expense = expenseMapper.mapToExpense(request, userId);
        expenseRepository.save(expense);

        final BigDecimal amountPerPayer = getAmountPerUser(request.getTotal(),
            payers.size() + EXPENSE_PAYER_USER);

        payers.forEach(payer -> {
            final UserPaysExpense userPaysExpense = userPaysExpenseMapper.mapToUserPaysExpense(payer, expense,
                amountPerPayer);
            userPaysExpenseRepository.save(userPaysExpense);
        });

        return expenseMapper.mapToRegisterExpenseResponse(expense);
    }

    public List<DebtDTO> getDebts(final Long expenseId) {
        List<DebtDTO> debtDTOS = new ArrayList<>();

        userPaysExpenseRepository.findAllByExpenseId(expenseId).forEach(userPaysExpense -> {
            DebtDTO debtDTO = expenseMapper.mapToDebtDTO(userPaysExpense);
            debtDTOS.add(debtDTO);
        });

        return debtDTOS;
    }

    private BigDecimal getAmountPerUser(final BigDecimal total, final int payers) {
        return total.divide(BigDecimal.valueOf(payers), HALF_UP).setScale(2, HALF_UP);
    }

}
