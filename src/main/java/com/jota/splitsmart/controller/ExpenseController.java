package com.jota.splitsmart.controller;

import com.jota.splitsmart.service.expenseservice.ExpenseService;
import com.jota.splitsmart.service.expenseservice.dto.ExpenseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ExpenseDTO register(@RequestBody final ExpenseDTO expenseDTO) {
        return expenseService.register(expenseDTO);
    }
}
