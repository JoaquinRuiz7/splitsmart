package com.jota.splitsmart.service.authservice.response;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private Long userId;
    private String token;
}
