package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.*;
import com.digibank.restapi.model.entity.User;

public interface OtpService {
    OtpResponseDto register(OtpDto otpDto);
    OtpVerificationDto verifyOtp(User idUser, OtpVerificationDto otpVerificationDto);
    String regenerateOtp(User idUser);

}