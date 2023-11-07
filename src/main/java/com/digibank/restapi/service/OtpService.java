package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;

public interface OtpService {
    OtpDto register(OtpDto otpDto);
    OtpVerificationDto verifyOtp(Long id_otp, OtpDto otpDto);
//    String regenerateOtp(String email);
}