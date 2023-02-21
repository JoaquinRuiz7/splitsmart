package com.jota.splitsmart.service.debtsservice.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DebtDTO {

    private String description;
    private String payTo;
    private BigDecimal amount;
    private Boolean isPayed;
}
