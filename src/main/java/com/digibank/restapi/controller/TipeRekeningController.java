package com.digibank.restapi.controller;


import com.digibank.restapi.dto.TypeRekenignDto;
import com.digibank.restapi.service.TypeRekeningService;
import com.digibank.restapi.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class TipeRekeningController {

    private TypeRekeningService typeRekeningService;

    @GetMapping("/type-cards")
    public ResponseEntity<Object> getTypeRekening() {
        return ResponseHandler.getTypeRekening(typeRekeningService.getAllTypeRekening());
    }

}
