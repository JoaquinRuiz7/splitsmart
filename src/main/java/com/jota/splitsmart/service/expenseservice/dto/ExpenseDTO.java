package com.jota.splitsmart.service.expenseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExpenseDTO {

    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    private Double total;
}
