package com.digibank.restapi.dto.otp;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class OtpDto {
    private String email;
    private String otp;
    private LocalDateTime cretaedOtp;
}
