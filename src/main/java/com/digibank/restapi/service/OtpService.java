package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpRegenerateDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.model.entity.User;

public interface OtpService {
    OtpDto register(OtpDto otpDto);
    OtpVerificationDto verifyOtp(Long id_otp, OtpDto otpDto);

    String regenerateOtp(OtpRegenerateDto otpRegenerateDto, User idUser);

}