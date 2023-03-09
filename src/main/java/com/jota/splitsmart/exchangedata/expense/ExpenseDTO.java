package com.jota.splitsmart.exchangedata.expense;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ExpenseDTO {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private List<Long> payers;
    private String description;

}