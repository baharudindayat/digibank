package com.digibank.restapi.controller;


import com.digibank.restapi.dto.CreateMpinDto;
import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.dto.login.LoginReqDto;
import com.digibank.restapi.service.AuthenticationService;
import com.digibank.restapi.service.CreateMpinService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final CreateMpinService createMpinService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReqDto request) {
        LoginResDto newResponse = authenticationService.login(request);
        return ResponseHandler.loginResponse("Login Berhasil", HttpStatus.OK, newResponse);
    }

    @PutMapping("/{id}/mpin")
    public ResponseEntity<Object> createMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable Long id) {
        createMpinService.createMpin(id, createMpinDto);
        return ResponseHandler.createMpin("MPIN berhasil dibuat", HttpStatus.OK);
    }

    @PostMapping("/{id}/confirm-mpin")
    public ResponseEntity<Object> confirmMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable Long id) {
        createMpinService.confirmMpin(id, createMpinDto);
        return ResponseHandler.createMpin("MPIN terkonfimasi", HttpStatus.OK);
    }
}
