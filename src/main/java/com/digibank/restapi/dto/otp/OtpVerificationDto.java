package com.digibank.restapi.dto.otp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerificationDto {
    private String message;

    public OtpVerificationDto(String message) {
        this.message = message;
    }
}