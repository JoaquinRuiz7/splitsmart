package com.jota.splitsmart.exchangedata.debts;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExpenseDebtDTO {

    private String description;
    private String userName;
    private BigDecimal amount;
    private Boolean isPayed;
}
