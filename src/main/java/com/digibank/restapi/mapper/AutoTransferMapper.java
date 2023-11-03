package com.digibank.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoTransferMapper {

    AutoTransferMapper MAPPER = Mappers.getMapper(AutoTransferMapper.class);


}
