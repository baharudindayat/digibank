package com.digibank.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {

    private String email;
    private String password;

    private String newpassword;
    private String oldpassword;
}
