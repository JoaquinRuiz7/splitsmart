package com.jota.splitsmart.controller;

import com.jota.splitsmart.exchangedata.debts.ExpenseDebtDTO;
import com.jota.splitsmart.exchangedata.debts.UserDebtDTO;
import com.jota.splitsmart.security.SecurityGuard;
import com.jota.splitsmart.service.DebtsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/debts")
@RequiredArgsConstructor
public class DebtsController {

    private final DebtsService debtsService;
    private final SecurityGuard securityGuard;

    @GetMapping("/{userId}/user-debt")
    public List<UserDebtDTO> getUserDebts(@PathVariable final Long userId) {
        securityGuard.checkIfTokenBelongsToUser(userId);
        return debtsService.getUserDebts(userId);
    }

    @GetMapping("/{expenseId}/expense-debt")
    public List<ExpenseDebtDTO> getExpenseDebts(@PathVariable final Long expenseId) {
        securityGuard.checkIfDebtBelongsToUser(expenseId);
        return debtsService.getExpenseDebts(expenseId);
    }

    @DeleteMapping("/{userId}/remove-from-expense/{expenseId}")
    public void removePayerFromExpense(@PathVariable final Long userId, @PathVariable final Long expenseId) {
        securityGuard.checkIfTokenBelongsToUser(userId);
        securityGuard.checkIfDebtBelongsToUser(expenseId);
        debtsService.removeUser(userId, expenseId);
    }

}
