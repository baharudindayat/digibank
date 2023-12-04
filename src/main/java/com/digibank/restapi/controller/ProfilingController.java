package com.digibank.restapi.controller;

import com.digibank.restapi.dto.getAccount.GetAccountsDto;
import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.service.GetAccountsService;
import com.digibank.restapi.service.PasswordService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/profiling")
public class ProfilingController {

    private PasswordService passwordService;
    private GetAccountsService getAccountsService;

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
    ){
        String authToken = token.substring(7);
        GetAccountsDto newGetAccounts = getAccountsService.getAccounts(authToken);
        return ResponseHandler.getAccounts("Sukses", HttpStatus.OK, newGetAccounts);
    }


}
