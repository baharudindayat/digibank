package com.digibank.restapi.controller;

import com.digibank.restapi.dto.cif.CifDto;
import com.digibank.restapi.dto.mpin.CreateMpinDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningResDto;
import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.dto.login.LoginReqDto;
import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpResponseDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.*;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final AuthenticationService authenticationService;
    private final CreateMpinService createMpinService;
    private final OtpService userService;
    private final PasswordService passwordService;
    private final TypeRekeningService typeRekeningService;
    private final CifService cifService;
    private final ConfirmRekeningService confirmRekeningService;

    @GetMapping("/cards")
    public ResponseEntity<Object> getTypeRekening() {
        return ResponseHandler.getTypeRekening(typeRekeningService.getAllTypeRekening());
    }

    @PostMapping("/otp-generate")
    public ResponseEntity<Object> register(@RequestBody OtpDto registerDto) {
        OtpResponseDto newOtp = userService.register(registerDto);
        return ResponseHandler.generateResponseCreate(HttpStatus.CREATED, "OTP berhasil terkirim", newOtp);
    }


    @PutMapping("/{idUser}/otp-verification")
    public ResponseEntity<Object> verifyOtp(@PathVariable(required = false) User idUser, @RequestBody OtpVerificationDto otpVerificationDto) {
        userService.verifyOtp(idUser, otpVerificationDto);
        return ResponseHandler.generateResponseVerivyOtp(HttpStatus.OK, "Email berhasil diverifikasi");
    }

    @PutMapping("/{idUser}/otp-regenerate")
    public ResponseEntity<Object> regenerateOtp(@PathVariable(required = false) User idUser) {

        String message =  userService.regenerateOtp(idUser);
        return ResponseHandler.generateResponseVerivyOtp(HttpStatus.OK, message);
    }

    @PutMapping("/{idUser}/password")
    public ResponseEntity<Object> changePassword(
            @PathVariable(required = false) Long idUser,
            @RequestBody CreatePasswordDto createPasswordRequest) {
            passwordService.changePassword(idUser, createPasswordRequest);
            return ResponseHandler.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Disimpan");
    }

    @PostMapping("{idUser}/tipe-rekening/{idTipe}/cif")
    public ResponseEntity<Object> createCif(
            @RequestBody CifDto cifDto,
            @PathVariable long idUser,
            @PathVariable long idTipe) {
        String newCif = cifService.createCif(cifDto, idUser, idTipe);
        return ResponseHandler.generateResponseCif("CIF Berhasil Dibuat", HttpStatus.OK, newCif);
    }

    @PutMapping("/{idUser}/mpin")
    public ResponseEntity<Object> createMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable(required = false) Long idUser) {
        createMpinService.createMpin(idUser, createMpinDto);
        return ResponseHandler.createMpin("Selamat Akun Berhasil dibuat Silahkan Masuk Akun", HttpStatus.OK);
    }

    @PostMapping("/{idUser}/confirm-mpin")
    public ResponseEntity<Object> confirmMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable(required = false) Long idUser) {
        createMpinService.confirmMpin(idUser, createMpinDto);
        return ResponseHandler.createMpin("MPIN terkonfimasi", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReqDto request) {
        LoginResDto newResponse = authenticationService.login(request);
        return ResponseHandler.loginResponse("Login Berhasil!", HttpStatus.OK, newResponse);
    }


    @PostMapping("/confirm-accounts")
    public ResponseEntity<Object> confirmRekening(@RequestBody ConfirmRekeningReqDto confirmRekeningReqDto) {
        ConfirmRekeningResDto confirmRekeningResDto = confirmRekeningService.confirmRekening(confirmRekeningReqDto);
        return ResponseHandler.loginResponse("Sukses", HttpStatus.OK, confirmRekeningResDto);
    }
}