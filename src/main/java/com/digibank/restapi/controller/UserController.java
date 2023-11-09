package com.digibank.restapi.controller;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpRegenerateDto;
import com.digibank.restapi.dto.otp.OtpResponseDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.OtpException.FailedException;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.*;
import com.digibank.restapi.utils.ResponseOtp.ResponseHandlerOtp;
import com.digibank.restapi.utils.ResponseOtp.ResponseHandlerVerivyOtp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;

import com.digibank.restapi.dto.CreateMpinDto;
import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.dto.login.LoginReqDto;
import com.digibank.restapi.utils.ResponseHandler;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final CreateMpinService createMpinService;

    private OtpService userService;
    private PasswordService passwordService;

    private TypeRekeningService typeRekeningService;

    @GetMapping("/cards")
    public ResponseEntity<Object> getTypeRekening() {
        return ResponseHandler.getTypeRekening(typeRekeningService.getAllTypeRekening());
    }
    
    @PostMapping("/otp-generate")
    public ResponseEntity<Object> register(@RequestBody OtpDto registerDto) {
        OtpResponseDto newOtp = userService.register(registerDto);
        return ResponseHandlerOtp.generateResponseCreate(HttpStatus.CREATED, "Otp berhasil terkirim", newOtp);
    }

    @PutMapping("/{idUser}/otp-verification")
    public ResponseEntity<Object> verifyOtp(@PathVariable User idUser, @RequestBody OtpDto otpDto) {
        try {
            OtpVerificationDto verificationResult = userService.verifyOtp(idUser, otpDto);

            // Verifikasi OTP berhasil
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, verificationResult.getMessage());
        } catch (FailedException e) {
            // Verifikasi OTP gagal
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{idUser}/otp-regenerate")
    public ResponseEntity<Object> regenerateOtp(@RequestBody OtpRegenerateDto regenerateDto, @PathVariable(required = false) User idUser) {

        String message =  userService.regenerateOtp(regenerateDto, idUser);
        return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, message);
    }

    @PutMapping("/{id_user}/password")
    public ResponseEntity<Object> changePassword(
            @PathVariable Long id_user,
            @RequestBody CreatePasswordDto createPasswordRequest) {
            CreatePasswordDto response = passwordService.changePassword(id_user, createPasswordRequest);
            return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Disimpan");
    }

    @PutMapping("/{idUser}/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable Long idUser
    ) {
        passwordService.changePasswordWithValidation(idUser, changePasswordDto);
        return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Diupdate");
    }


    @PutMapping("/{idUser}/mpin")
    public ResponseEntity<Object> createMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable Long idUser) {
        createMpinService.createMpin(idUser, createMpinDto);
        return ResponseHandler.createMpin("MPIN berhasil dibuat", HttpStatus.OK);
    }

    @PostMapping("/{id}/confirm-mpin")
    public ResponseEntity<Object> confirmMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable Long id) {
        createMpinService.confirmMpin(id, createMpinDto);
        return ResponseHandler.createMpin("MPIN terkonfimasi", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReqDto request) {
        LoginResDto newResponse = authenticationService.login(request);
        return ResponseHandler.loginResponse("Login Berhasil", HttpStatus.OK, newResponse);
    }
}

