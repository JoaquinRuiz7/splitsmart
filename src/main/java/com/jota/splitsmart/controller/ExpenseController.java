package com.jota.splitsmart.controller;

import static com.jota.splitsmart.context.ContextData.EXPENSE_PAYER_ID;

import com.jota.splitsmart.service.expenseservice.ExpenseService;
import com.jota.splitsmart.service.expenseservice.dto.DebtDTO;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import com.jota.splitsmart.service.expenseservice.request.UpdateExpenseRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{expenseId}/debt")
    public List<DebtDTO> getDebt(@PathVariable final Long expenseId) {
        return expenseService.getDebts(expenseId);
    }

    @PatchMapping("/{expenseId}")
    public ExpenseDTO updateExpense(@PathVariable final Long expenseId,
        @RequestBody final UpdateExpenseRequest updateExpenseRequest) {
        return expenseService.updateExpense(expenseId, updateExpenseRequest);
    }

    @DeleteMapping("/{userId}/remove-user/{expenseId}")
    public void removePayerFromExpense(@PathVariable final Long userId,@PathVariable final Long expenseId){
        expenseService.removeUser(userId,expenseId);
    }

}
