package com.jota.splitsmart.service.userservice.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String password;
}
