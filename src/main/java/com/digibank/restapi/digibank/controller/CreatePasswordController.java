package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.dto.CreatePasswordDto;
import com.digibank.restapi.digibank.service.CreatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePasswordController {
    @Autowired
    private CreatePasswordService createPasswordService;

    @PutMapping("/users/password")
    public ResponseEntity<String> changePassword(@RequestParam Integer id_user, @RequestBody CreatePasswordDto createPasswordDto) {
        return new ResponseEntity<>(createPasswordService.changePassword(id_user, createPasswordDto.getPassword()), HttpStatus.OK);
    }
}
