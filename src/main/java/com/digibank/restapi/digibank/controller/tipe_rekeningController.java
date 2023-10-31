package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.dto.tipe_rekeningDto;
import com.digibank.restapi.digibank.service.tipe_rekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/tipe-rekening")
public class tipe_rekeningController {
    @Autowired
    private tipe_rekeningService tipeRekeningService;

    @GetMapping
    public ResponseEntity<List<tipe_rekeningDto>> getAllTipeRekening() {
        List<tipe_rekeningDto> tipeRekenings = tipeRekeningService.getAllTipeRekening();
        return new ResponseEntity<>(tipeRekenings, HttpStatus.OK);
    }
}
