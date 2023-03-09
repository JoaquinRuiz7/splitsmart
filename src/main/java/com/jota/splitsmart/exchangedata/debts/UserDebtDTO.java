package com.jota.splitsmart.exchangedata.debts;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UserDebtDTO {

    private String description;
    private String payTo;
    private BigDecimal amount;
    private Boolean isPayed;
}
