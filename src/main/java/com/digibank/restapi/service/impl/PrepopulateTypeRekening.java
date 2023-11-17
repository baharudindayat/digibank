//package com.digibank.restapi.service.impl;
//
//import com.digibank.restapi.model.entity.TypeRekening;
//import com.digibank.restapi.repository.TypeRekeningRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class PrepopulateTypeRekening implements CommandLineRunner {
//
//    private TypeRekeningRepository typeRekeningRepository;
//
//    @Override
//    public void run(String... args) {
//        List<TypeRekening> typeRekenings = new ArrayList<>();
//        TypeRekening typeRekening = new TypeRekening();
//        typeRekening.setIdTipe(1);
//        typeRekening.setNamaTipe("Silver");
//        typeRekening.setLimitTransfer("5 Juta");
//        typeRekenings.add(typeRekening);
//        typeRekeningRepository.saveAll(typeRekenings);
//
//        typeRekening.setIdTipe(2);
//        typeRekening.setNamaTipe("Gold");
//        typeRekening.setLimitTransfer("10 Juta");
//        typeRekenings.add(typeRekening);
//        typeRekeningRepository.saveAll(typeRekenings);
//
//        typeRekening.setIdTipe(3);
//        typeRekening.setNamaTipe("Platinum");
//        typeRekening.setLimitTransfer("15 Juta");
//        typeRekenings.add(typeRekening);
//        typeRekeningRepository.saveAll(typeRekenings);
//    }
//}
