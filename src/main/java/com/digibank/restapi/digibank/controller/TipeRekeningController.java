package com.digibank.restapi.digibank.controller;

import com.digibank.restapi.digibank.dto.TipeRekeningDto;
import com.digibank.restapi.digibank.service.TipeRekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/cards")
public class TipeRekeningController {
    @Autowired
    private TipeRekeningService tipeRekeningService;

    @GetMapping
    public ResponseEntity<List<TipeRekeningDto>> getAllTipeRekening() {
        List<TipeRekeningDto> tipeRekenings = tipeRekeningService.getAllTipeRekening();
        return new ResponseEntity<>(tipeRekenings, HttpStatus.OK);
    }
}
