package com.digibank.restapi.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

    public String generateOtp() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // Rentang angka 1000 hingga 9999
        String otp = String.format("%04d", randomNumber); // Format sebagai 4 digit dengan leading zero
        return otp;
    }
}
