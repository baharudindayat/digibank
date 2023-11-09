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
@RequestMapping("api/transfer")
public class TransferController {

    @GetMapping("/home")
    public String sayHello() {
        return "hello world";
    }

}
