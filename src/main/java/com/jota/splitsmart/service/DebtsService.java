package com.jota.splitsmart.service;

import static java.math.RoundingMode.HALF_UP;

import com.jota.splitsmart.exchangedata.debts.ExpenseDebtDTO;
import com.jota.splitsmart.exchangedata.debts.UserDebtDTO;
import com.jota.splitsmart.mapper.DebtsMapper;
import com.jota.splitsmart.persistence.model.Debts;
import com.jota.splitsmart.persistence.repository.DebtsRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DebtsService {

    private final DebtsRepository debtsRepository;
    private final DebtsMapper debtMapper;

    private static final int EXPENSE_PAYER_USER = 1;


    public List<UserDebtDTO> getUserDebts(final Long userId) {
        return debtsRepository.getUserDebts(userId).stream().map(debtMapper::mapToUserDebtDTO).toList();
    }

    public List<ExpenseDebtDTO> getExpenseDebts(final Long expenseId) {
        return debtsRepository.findAllByExpenseId(expenseId).stream().map(debtMapper::mapToExpenseDebtDTO).toList();
    }

    public void removeUser(final Long userId, final Long expenseId) {
        Debts userPaysExpense = debtsRepository.findByUserAndExpense(userId, expenseId);
        debtsRepository.delete(userPaysExpense);
        log.info("Deleted user with id {} from expense {}", userId, expenseId);
        updateAmountPerUser(expenseId, userPaysExpense.getExpense().getTotal());
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

    private BigDecimal getAmountPerUser(final BigDecimal total, final int payers) {
        return total.divide(BigDecimal.valueOf(payers + EXPENSE_PAYER_USER), HALF_UP)
            .setScale(2, HALF_UP);
    }

}
