package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.mpin.CreateMpinDto;
import com.digibank.restapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateMpinMapper {

    CreateMpinMapper MAPPER = Mappers.getMapper(CreateMpinMapper.class);

    CreateMpinDto mapToCreateMpinDto(User user);



}
