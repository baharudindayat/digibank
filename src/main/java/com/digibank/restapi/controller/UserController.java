package com.digibank.restapi.controller;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.utils.ResponseCifHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private CifService cifService;
    @PostMapping("/cif")
    public ResponseEntity<Object> createCif(@RequestBody CifDto cifDto) {

        String newCif = cifService.createCif(cifDto);

        return ResponseCifHandler.generateResponseCif("CIF Berhasil Dibuat", HttpStatus.OK, newCif);
    }
}