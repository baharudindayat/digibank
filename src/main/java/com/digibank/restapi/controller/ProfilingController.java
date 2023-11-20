package com.digibank.restapi.controller;

import com.digibank.restapi.dto.GetAccountsDto;
import com.digibank.restapi.dto.changePassword.ChangePasswordDto;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.GetAccountsService;
import com.digibank.restapi.service.PasswordService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/profiling")
public class ProfilingController {

    private PasswordService passwordService;
    private GetAccountsService getAccountsService;

    @PutMapping("/{idUser}/change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody ChangePasswordDto changePasswordDto,
            @PathVariable(required = false) Long idUser
    ) {
        passwordService.changePasswordWithValidation(idUser, changePasswordDto);
        return ResponseHandler.generateResponseVerivyOtp(HttpStatus.OK, "Kata Sandi Berhasil Diubah");
    }

    @GetMapping("/{idUser}/accounts")
    public ResponseEntity<Object> getAccounts(
            @PathVariable(required = false) User idUser
    ){
        GetAccountsDto newGetAccounts = getAccountsService.getAccounts(idUser);
        return ResponseHandler.getAccounts("Sukses", HttpStatus.OK, newGetAccounts);
    }


}
