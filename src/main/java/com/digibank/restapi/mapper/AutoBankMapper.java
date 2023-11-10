package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.BankDto;
import com.digibank.restapi.model.entity.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoBankMapper {

    AutoBankMapper MAPPER = Mappers.getMapper(AutoBankMapper.class);

    BankDto mapToBankDto(Bank bank);

    Bank mapToBank(BankDto bankDto);



}
