package com.digibank.restapi.controller;

import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.repository.CifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1")
public class CifController {

    @Autowired
    private CifRepository cifRepository;

    @PostMapping("/users/cif")
    public ResponseEntity<CIF> addCif(@RequestBody CIF cif) {
        if (cif == null) {
            return ResponseEntity.badRequest().build();
        }

        CIF savedCif = cifRepository.save(cif);
        if (savedCif == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(savedCif);
    }
}