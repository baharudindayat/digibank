package com.digibank.restapi.mapper;

import com.digibank.restapi.model.entity.TypeRekening;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeRekeningMapper {

    TypeRekeningMapper MAPPER = Mappers.getMapper(TypeRekeningMapper.class);

    TypeRekening mapToTypeRekeningDto(TypeRekening typeRekening);
}
