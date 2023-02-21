package com.jota.splitsmart.controller;

import static com.jota.splitsmart.context.ContextData.EXPENSE_PAYER_ID;

import com.jota.splitsmart.service.expenseservice.ExpenseService;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import com.jota.splitsmart.service.expenseservice.request.UpdateExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseDTO register(@RequestHeader(name = EXPENSE_PAYER_ID) final Long userId,
        @RequestBody final ExpenseDTO expenseDTO) {
        return expenseService.register(userId, expenseDTO);
    }

    @PatchMapping("/{expenseId}")
    public ExpenseDTO updateExpense(@PathVariable final Long expenseId,
        @RequestBody final UpdateExpenseRequest updateExpenseRequest) {
        return expenseService.updateExpense(expenseId, updateExpenseRequest);
    }

    @DeleteMapping("/{userId}/remove-from-debt/{expenseId}")
    public void removePayerFromExpense(@PathVariable final Long userId, @PathVariable final Long expenseId) {
        expenseService.removeUser(userId, expenseId);
    }

}
