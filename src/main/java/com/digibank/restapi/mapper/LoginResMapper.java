package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginResMapper {
    LoginResMapper MAPPER = Mappers.getMapper(LoginResMapper.class);

    LoginResDto mapToLoginResDto(LoginResDto loginResDto);
}
