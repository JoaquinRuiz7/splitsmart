package com.jota.splitsmart.exchangedata.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private Long userId;
    private String token;
}
