package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.model.entity.UserOTP;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOtpMapper {
    UserOtpMapper MAPPER = Mappers.getMapper(UserOtpMapper.class);

    //    OtpDto mapToOtpDto(UserOtp userOtp);

    UserOTP mapToUserOtp(OtpDto otpDto);


}
