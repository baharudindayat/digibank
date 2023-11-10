package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.mapper.AutoCifMapper;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
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
    private final RekeningRepository rekeningRepository;

    @Override
    public String createCif(CifDto cifDto) {

        CIF cif = AutoCifMapper.MAPPER.mapToCif(cifDto);

        repository.save(cif);

        Optional<CIF> idCif = repository.findByNik(cifDto.getNik());

        String noRekening = noRekGenerator.generateRekening();


        Rekening rekening = new Rekening();
        rekening.setNoRekening(Long.parseLong(noRekening));
        rekening.setIdCif(idCif.get());
        rekening.setTipeRekening(cifDto.getIdTipe());
        rekening.setSaldo(0.0);

        rekeningRepository.save(rekening);

        return noRekening;
    }
}
