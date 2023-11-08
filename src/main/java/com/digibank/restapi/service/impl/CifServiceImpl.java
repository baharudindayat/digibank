package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.mapper.AutoCifMapper;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CifService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CifServiceImpl implements CifService {

    private final CifRepository repository;
    private final AutoCifMapper autoCifMapper;
    private final UserRepository userRepository;

//    @Autowired
//    public CifServiceImpl(CifRepository repository, AutoCifMapper autoCifMapper,UserRepository userRepository) {
//        this.repository = repository;
//        this.autoCifMapper = autoCifMapper;
//        this.userRepository = userRepository;
//    }

    @Override
    public CifDto createCif(CifDto cifDto) {
        CIF cif = autoCifMapper.MAPPER.mapToCif(cifDto);
        Optional<CIF> newCif = repository.findById(cif.getId_cif());
        Optional<User> user = userRepository.findById(cifDto.getIdUsers().getIdUsers());

        cif.setIdUsers(cifDto.getIdUsers());



        // Assuming you have a valid user entity or user ID to set for idUsers
        // Set the idUsers property of CIF from the idUsers property of CifDto


        CIF savedCif = repository.save(cif);
        return autoCifMapper.MAPPER.mapToCifDto(savedCif);
    }


}
