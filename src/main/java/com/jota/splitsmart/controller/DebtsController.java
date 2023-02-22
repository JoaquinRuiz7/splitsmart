package com.jota.splitsmart.controller;

import com.jota.splitsmart.service.debtsservice.DebtsService;
import com.jota.splitsmart.service.debtsservice.response.ExpenseDebtDTO;
import com.jota.splitsmart.service.debtsservice.response.UserDebtDTO;
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

    @GetMapping("/{userId}/user-debt")
    public List<UserDebtDTO> getUserDebts(@PathVariable final Long userId) {
        return debtsService.getUserDebts(userId);
    }

    @GetMapping("/{expenseId}/expense-debt")
    public List<ExpenseDebtDTO> getExpenseDebts(@PathVariable final Long expenseId) {
        return debtsService.getExpenseDebts(expenseId);
    }

    @DeleteMapping("/{userId}/remove-from-expense/{expenseId}")
    public void removePayerFromExpense(@PathVariable final Long userId, @PathVariable final Long expenseId) {
        debtsService.removeUser(userId, expenseId);
    }

}
