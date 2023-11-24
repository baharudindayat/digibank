package com.digibank.restapi.controller;

import com.digibank.restapi.config.CustomAuthenticationEntryPoint;
import com.digibank.restapi.dto.GetAccountsDto;
import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.GetAccountsService;
import com.digibank.restapi.service.PasswordService;
import com.digibank.restapi.utils.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/profiling")
public class ProfilingController {

    private PasswordService passwordService;
    private GetAccountsService getAccountsService;
    CustomAuthenticationEntryPoint authenticationEntryPoint;

    @PutMapping("/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody ChangePasswordDto changePasswordDto
    ) {
        String authToken = token.substring(7);
        passwordService.changePasswordWithValidation(authToken, changePasswordDto);
        return ResponseHandler.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Diubah");
    }

    @GetMapping("/accounts")
    public ResponseEntity<Object> getAccounts(
            @RequestHeader("Authorization") String token

    )  {
        if(token.isEmpty()) {

        }
        String authToken = token.substring(7);
        GetAccountsDto newGetAccounts = getAccountsService.getAccounts(authToken);
        return ResponseHandler.getAccounts("Sukses", HttpStatus.OK, newGetAccounts);
    }


}
