package com.digibank.restapi.exception.OtpException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpResponse {
    private int status;
    private String id_user;
    private String email;
    private String otp;
    private String message;

}

