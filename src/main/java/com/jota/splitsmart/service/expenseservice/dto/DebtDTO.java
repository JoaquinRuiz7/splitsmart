package com.jota.splitsmart.service.expenseservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DebtDTO {

    private String description;
    private String userName;
    private BigDecimal amount;
    private Boolean isPayed;
}
