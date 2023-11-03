package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.OtpDto;

public interface OtpService {
    OtpDto register(OtpDto otpDto);
    String verifyAccount(String email, String otp);
    String regenerateOtp(String email);
}