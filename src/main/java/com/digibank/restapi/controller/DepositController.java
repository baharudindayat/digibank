package com.digibank.restapi.controller;


import com.digibank.restapi.dto.deposito.DepositoReqDto;
import com.digibank.restapi.service.DepositoService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/deposit")
public class DepositController {

    private final DepositoService depositoService;

    @PostMapping("/setor")
    public ResponseEntity<Object> deposit( @RequestBody DepositoReqDto depositoReqDto) {
        String response = depositoService.addSaldo(depositoReqDto);
        return ResponseHandler.addSaldo(response, HttpStatus.OK);
    }

}
