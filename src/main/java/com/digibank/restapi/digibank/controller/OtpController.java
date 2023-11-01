package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.dto.OtpDto;
import com.digibank.restapi.digibank.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/users/otp-generate")
    public ResponseEntity<String> register(@RequestBody OtpDto registerDto) {
        return new ResponseEntity<>(otpService.register(registerDto), HttpStatus.OK);
    }

    @PutMapping("/users/otp-verification")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(otpService.verifyAccount(email, otp), HttpStatus.OK);
    }

    @PutMapping("/users/otp-regenerate")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(otpService.regenerateOtp(email), HttpStatus.OK);
    }

}