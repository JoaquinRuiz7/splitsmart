package com.jota.splitsmart.exchangedata.user;

import lombok.Data;

@Data
public class RegisterUserResponse {

    private Long id;
    private String name;
    private String email;
    private String cellphone;

}
