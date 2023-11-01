package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.dto.RegisterDto;
import com.digibank.restapi.digibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/otp-generate")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
    }

    @PutMapping("/users/otp-verification")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }

    @PutMapping("/users/otp-regenerate")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }
}