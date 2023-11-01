package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePassword {
    @Autowired
    private ChangePasswordService changePasswordService;

    @PutMapping("/users/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam Integer id_user,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        return new ResponseEntity<>(changePasswordService.changePasswordWithValidation(id_user, oldPassword, newPassword), HttpStatus.OK);
    }
}
