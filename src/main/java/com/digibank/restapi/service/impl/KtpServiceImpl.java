package com.digibank.restapi.service.impl;


import com.digibank.restapi.dto.KtpDto;
import com.digibank.restapi.exception.NikNotFoundException;
import com.digibank.restapi.mapper.AutoKtpMapper;
import com.digibank.restapi.model.Ktp;
import com.digibank.restapi.repository.KtpRepository;
import com.digibank.restapi.service.KtpService;
import com.digibank.restapi.utils.NikGenerator;
import lombok.AllArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class KtpServiceImpl implements KtpService {

    private KtpRepository repository;
    private NikGenerator nikGenerator;

    @Override
    public KtpDto createKtp(KtpDto ktpDto) {

        Optional<Ktp> OptionalKtp = repository.findByNik(ktpDto.getNik());

        if (OptionalKtp.isPresent()) {
            throw new OpenApiResourceNotFoundException("NIK already exists");
        }

        String nikGenerator = this.nikGenerator.generateNik();
        ktpDto.setNik(nikGenerator);
        Ktp ktp = AutoKtpMapper.MAPPER.mapToKtp(ktpDto);
        Ktp savedKtp = repository.save(ktp);

        return AutoKtpMapper.MAPPER.mapToKtpDto(savedKtp);
    }

    @Override
    public Object getKtpById(String id) {
        Optional<Ktp> OptionalKtp = repository.findByNik(id);

        if (OptionalKtp.isPresent()) {
            Ktp ktp = OptionalKtp.get();
            return AutoKtpMapper.MAPPER.mapToDigibankDto(ktp);
        }else {
            throw new NikNotFoundException("NIK Tidak Ditemukan")  ;
        }
    }
}
