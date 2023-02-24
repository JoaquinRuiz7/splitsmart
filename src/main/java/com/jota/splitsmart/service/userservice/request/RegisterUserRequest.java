package com.jota.splitsmart.service.userservice.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String password;
}
