package com.digibank.restapi.digibank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpDto {

    private String email;

    private String newpassword;
    private String oldpassword;
}
