package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.model.entity.CIF;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AutoCifMapper {

    CifDto mapToCifDto(CIF cif);

    CIF mapToCif(CifDto cifDto);

    AutoCifMapper MAPPER = Mappers.getMapper(AutoCifMapper.class);

}
