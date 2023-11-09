package com.digibank.restapi.service.impl;

import com.digibank.restapi.model.entity.TypeRekening;
import com.digibank.restapi.repository.TypeRekeningRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PrepopulateTypeRekening implements CommandLineRunner {

    private TypeRekeningRepository typeRekeningRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TypeRekening> typeRekenings = new ArrayList<>();
        TypeRekening typeRekening = new TypeRekening();
        typeRekening.setIdTipe(1L);
        typeRekening.setNamaTipe("Silver");
        typeRekening.setLimitTransfer("5 Juta");
        typeRekenings.add(typeRekening);
        typeRekeningRepository.saveAll(typeRekenings);
        typeRekening.setIdTipe(2L);
        typeRekening.setNamaTipe("Gold");
        typeRekening.setLimitTransfer("10 Juta");
        typeRekenings.add(typeRekening);
        typeRekeningRepository.saveAll(typeRekenings);
        typeRekening.setIdTipe(3L);
        typeRekening.setNamaTipe("Platinum");
        typeRekening.setLimitTransfer("15 Juta");
        typeRekenings.add(typeRekening);
        typeRekeningRepository.saveAll(typeRekenings);
    }
}
