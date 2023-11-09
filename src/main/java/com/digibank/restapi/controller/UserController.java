package com.digibank.restapi.controller;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpRegenerateDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.OtpException.FailedException;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.service.PasswordService;
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
    private PasswordService passwordService;

    @PostMapping("/users/otp-generate")
    public ResponseEntity<Object> register(@RequestBody OtpDto registerDto) {
        OtpDto newOtp = userService.register(registerDto);
        return ResponseHandlerOtp.generateResponseCreate(HttpStatus.CREATED, "Otp berhasil terkirim", newOtp);
    }

    @PutMapping("/users/{id_otp}/otp-verification")
    public ResponseEntity<Object> verifyOtp(@PathVariable Long id_otp, @RequestBody OtpDto otpDto) {
        try {
            OtpVerificationDto verificationResult = userService.verifyOtp(id_otp, otpDto);

            // Verifikasi OTP berhasil
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, verificationResult.getMessage());
        } catch (FailedException e) {
            // Verifikasi OTP gagal
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/users/{idUser}/otp-regenerate")
    public ResponseEntity<Object> regenerateOtp(@RequestBody OtpRegenerateDto regenerateDto, @PathVariable(required = false) User idUser) {

        String message =  userService.regenerateOtp(regenerateDto, idUser);
        return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, message);
    }

    @PutMapping("/users/{id_user}")
    public ResponseEntity<Object> changePassword(
            @PathVariable Integer id_user,
            @RequestBody CreatePasswordDto createPasswordRequest) {
            CreatePasswordDto response = passwordService.changePassword(id_user, createPasswordRequest);
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Disimpan");
    }

    @PutMapping("/users/{idUser}/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable Integer idUser
    ) {
        passwordService.changePasswordWithValidation(idUser, changePasswordDto);
        return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Diupdate");    }
}