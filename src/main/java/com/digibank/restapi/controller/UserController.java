package com.digibank.restapi.controller;

import com.digibank.restapi.dto.createPassword.CreatePasswordDto;
import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.service.CreatePasswordService;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.utils.ResponseHandlerOtp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private OtpService userService;

    @Autowired
    private CreatePasswordService createPasswordService;

    @PostMapping("/users/otp-generate")
    public ResponseEntity<Object> register(@RequestBody OtpDto registerDto) {
        OtpDto newOtp = userService.register(registerDto);
        return ResponseHandlerOtp.generateResponseCreate("Otp berhasil terkirim", HttpStatus.CREATED, newOtp);
//                (userService.register(registerDto), HttpStatus.OK);
    }

    @PutMapping("/users/otp-verification")
    public ResponseEntity<Object> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }

    @PutMapping("/users/otp-regenerate")
    public ResponseEntity<Object> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

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