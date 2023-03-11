package com.jota.splitsmart.exchangedata.user;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String cellphone;
    @NotEmpty
    private String password;
}
