package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.model.entity.CIF;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AutoCifMapper {

    AutoCifMapper MAPPER = Mappers.getMapper(AutoCifMapper.class);

    CIF mapToCif(CifDto cifDto);


}

