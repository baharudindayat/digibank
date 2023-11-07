package com.digibank.restapi.controller;


import com.digibank.restapi.dto.CreateMpinDto;
import com.digibank.restapi.dto.login.JwtAuthenticationResponse;
import com.digibank.restapi.dto.login.LoginRequest;
import com.digibank.restapi.model.entity.User;
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
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PutMapping("/{id}/mpin")
    public ResponseEntity<Object> createMpin(
            @RequestBody CreateMpinDto createMpinDto, @PathVariable Long id) {
        createMpinService.createMpin(id, createMpinDto);
        return ResponseHandler.createMpin("Berhasil buat Mpin", HttpStatus.OK);
    }
}
