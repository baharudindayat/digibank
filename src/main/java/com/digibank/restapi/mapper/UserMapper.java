package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id_user", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "otp", source = "otp")
    @Mapping(target = "cretaedOtp", source = "cretaedOtp")
    User otpDtoToUser(OtpDto otpDto);
}
