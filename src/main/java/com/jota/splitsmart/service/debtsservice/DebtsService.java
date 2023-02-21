package com.jota.splitsmart.service.debtsservice;

import com.jota.splitsmart.mapper.DebtsMapper;
import com.jota.splitsmart.persistence.repository.DebtsRepository;
import com.jota.splitsmart.service.debtsservice.response.ExpenseDebtDTO;
import com.jota.splitsmart.service.debtsservice.response.UserDebtDTO;
import java.util.ArrayList;
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

    public List<UserDebtDTO> getUserDebts(final Long userId) {
        List<UserDebtDTO> debtDTOS = new ArrayList<>();
        debtsRepository.getUserDebts(userId).forEach(debt -> {
            UserDebtDTO debtDTO = debtMapper.mapToUserDebtDTO(debt);
            debtDTOS.add(debtDTO);
        });
        log.info("Successfully obtained debts data for user {}", userId);
        return debtDTOS;
    }

    public List<ExpenseDebtDTO> getExpenseDebts(final Long expenseId) {
        List<ExpenseDebtDTO> debtDTOS = new ArrayList<>();
        debtsRepository.findAllByExpenseId(expenseId).forEach(debt -> {
            ExpenseDebtDTO debtDTO = debtMapper.mapToExpenseDebtDTO(debt);
            debtDTOS.add(debtDTO);
        });
        return debtDTOS;
    }
}
