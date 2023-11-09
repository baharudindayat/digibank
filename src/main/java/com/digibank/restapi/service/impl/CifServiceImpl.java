package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.dto.CifResponseDto;
import com.digibank.restapi.mapper.AutoCifMapper;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.utils.NoRekGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CifServiceImpl implements CifService {

    private final CifRepository repository;
    private final AutoCifMapper autoCifMapper;
    private final NoRekGenerator noRekGenerator;

    @Override
    public CifResponseDto createCif(CifDto cifDto) {
        CIF cif = autoCifMapper.MAPPER.mapToCif(cifDto);

        cif.setIdUsers(cifDto.getIdUsers());

        CIF savedCif = repository.save(cif);

        String noRekening = noRekGenerator.generateRekening();

        return autoCifMapper.MAPPER.mapToCifResponseDto(savedCif, noRekening);
    }
}
