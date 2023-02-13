package com.jota.splitsmart.exchangedata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private int status;
    private String message;
}
