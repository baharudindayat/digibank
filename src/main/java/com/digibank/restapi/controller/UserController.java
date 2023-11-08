package com.digibank.restapi.controller;

import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.OtpException.OtpFailedException;
import com.digibank.restapi.service.CreatePasswordService;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.utils.ResponseOtp.ResponseHandlerOtp;
import com.digibank.restapi.utils.ResponseOtp.ResponseHandlerVerivyOtp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private OtpService userService;
    private CreatePasswordService createPasswordService;

    @PostMapping("/users/otp-generate")
    public ResponseEntity<Object> register(@RequestBody OtpDto registerDto) {
        OtpDto newOtp = userService.register(registerDto);
        return ResponseHandlerOtp.generateResponseCreate(HttpStatus.CREATED, "Otp berhasil terkirim", newOtp);
    }

    @PutMapping("/users/otp-verification/{id_otp}")
    public ResponseEntity<Object> verifyOtp(@PathVariable Long id_otp, @RequestBody OtpDto otpDto) {
        try {
            OtpVerificationDto verificationResult = userService.verifyOtp(id_otp, otpDto);

            // Verifikasi OTP berhasil
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, verificationResult.getMessage());
        } catch (OtpFailedException e) {
            // Verifikasi OTP gagal
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @PutMapping("/users/otp-regenerate")
//    public ResponseEntity<Object> regenerateOtp(@RequestParam String email) {
//        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
//    }

    @PutMapping("/users/password")
    public ResponseEntity<String> changePassword(@RequestParam Integer id_user, @RequestBody CreatePasswordDto userDto) {
        return new ResponseEntity<>(createPasswordService.changePassword(id_user, userDto.getPassword()), HttpStatus.OK);
    }

    @PutMapping("/users/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam Integer id_user,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        return new ResponseEntity<>(createPasswordService.changePasswordWithValidation(id_user, oldPassword, newPassword), HttpStatus.OK);
    }
}