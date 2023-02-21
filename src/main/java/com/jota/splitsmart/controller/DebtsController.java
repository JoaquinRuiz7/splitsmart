package com.jota.splitsmart.controller;

import com.jota.splitsmart.service.debtsservice.DebtsService;
import com.jota.splitsmart.service.debtsservice.response.DebtDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/debts")
@RequiredArgsConstructor
public class DebtsController {

    private final DebtsService userDebtsService;

    @GetMapping("/{userId}")
    public List<DebtDTO> getDebts(@PathVariable final Long userId) {
        return userDebtsService.getDebts(userId);
    }
}
