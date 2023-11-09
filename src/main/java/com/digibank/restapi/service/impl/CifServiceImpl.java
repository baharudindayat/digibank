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
    private final NoRekGenerator noRekGenerator;

    @Override
    public CifResponseDto createCif(CifDto cifDto) {

        CIF cif = AutoCifMapper.MAPPER.mapToCif(cifDto);
        CifResponseDto cifResponseDto = AutoCifMapper.MAPPER.mapToCifResponseDto(cif);

        repository.save(cif);

        Optional<CIF> idCif = repository.findByNik(cifDto.getNik());

        String noRekening = noRekGenerator.generateRekening();

        cifResponseDto.setIdCif(idCif.get().getId_cif());
        cifResponseDto.setNoRekening(noRekening);

        return cifResponseDto;
    }
}
