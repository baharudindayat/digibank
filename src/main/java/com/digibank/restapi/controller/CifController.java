package com.digibank.restapi.controller;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.utils.ResponseCifHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CifController {

    private CifService cifService;

    @PostMapping("/users/cif")
    public ResponseEntity<Object> createCif(@RequestBody CifDto cifDto) {
        CifDto newCif = cifService.createCif(cifDto);
        return ResponseCifHandler.generateResponseCif("CIF Berhasil Dibuat", HttpStatus.OK, newCif);
    }
}