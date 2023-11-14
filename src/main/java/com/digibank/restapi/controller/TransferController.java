package com.digibank.restapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/transfer")
public class TransferController {

    @GetMapping("/home")
    public String sayHello() {
        return "hello world";
    }

}
