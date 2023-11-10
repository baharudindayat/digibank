package com.digibank.restapi.controller;

import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.service.PasswordService;
import com.digibank.restapi.utils.ResponseOtp.ResponseHandlerVerivyOtp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/profiling")
public class ProfilingController {

    private PasswordService passwordService;
    @PutMapping("/{idUser}/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable(required = false) Long idUser
    ) {
        passwordService.changePasswordWithValidation(idUser, changePasswordDto);
        return ResponseHandlerVerivyOtp.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Diupdate");
    }

}
