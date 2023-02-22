package com.jota.splitsmart.service.debtsservice.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExpenseDebtDTO {

    private String description;
    private String userName;
    private BigDecimal amount;
    private Boolean isPayed;
}
