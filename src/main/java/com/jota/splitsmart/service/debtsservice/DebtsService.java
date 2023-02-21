package com.jota.splitsmart.service.debtsservice;

import com.jota.splitsmart.mapper.DebtsMapper;
import com.jota.splitsmart.persistence.repository.DebtsRepository;
import com.jota.splitsmart.service.debtsservice.response.DebtDTO;
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
    private final DebtsMapper userDebtMapper;

    public List<DebtDTO> getDebts(final Long userId) {
        List<DebtDTO> debtDTOS = new ArrayList<>();
        debtsRepository.getUserDebts(userId).forEach(debt -> {
            DebtDTO debtDTO = userDebtMapper.mapToDebtDTO(debt);
            debtDTOS.add(debtDTO);
        });
        log.info("Successfully obtained debts data for user {}", userId);
        return debtDTOS;
    }
}
