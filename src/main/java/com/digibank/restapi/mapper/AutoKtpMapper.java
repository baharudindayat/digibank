package com.digibank.restapi.mapper;


import com.digibank.restapi.dto.dukcapil.DigiBankGetDto;
import com.digibank.restapi.dto.dukcapil.KtpDto;
import com.digibank.restapi.model.entity.dukcapil.Ktp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoKtpMapper {
    AutoKtpMapper MAPPER = Mappers.getMapper(AutoKtpMapper.class);

    KtpDto mapToKtpDto(Ktp ktp);

    Ktp mapToKtp(KtpDto ktpDto);

    DigiBankGetDto mapToDigibankDto(Ktp ktp);

    Ktp mapToDigibank(DigiBankGetDto DigibankDto);
}
