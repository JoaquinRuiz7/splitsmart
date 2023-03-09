package com.jota.splitsmart.exchangedata.expense;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateExpenseRequest {

    @NotEmpty
    @NotNull
    private String description;

    @NotEmpty
    @NotNull
    @Positive
    private BigDecimal total;
}
