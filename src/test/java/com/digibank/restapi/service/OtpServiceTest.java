package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpResponseDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.service.impl.OtpServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class OtpServiceTest {

    @Mock
    private User mockUser;

    @Mock
    private OtpDto mockOtpDto;

    @Mock
    private OtpVerificationDto mockOtpVerivicationDto;

    @Mock
    private OtpServiceImpl otpServiceImpl;

    @InjectMocks
    private OtpServiceImpl otpService;

    @Test
    void testRegister() {

        OtpResponseDto expectedResponse = new OtpResponseDto();

        Mockito.when(otpServiceImpl.register(any(OtpDto.class)))
                .thenReturn(expectedResponse);

        OtpResponseDto actualResponse = otpService.register(mockOtpDto);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void testVerifyOtp() {

        OtpVerificationDto expectedResponse = new OtpVerificationDto();

        Mockito.when(otpServiceImpl.verifyOtp(any(User.class), any(OtpVerificationDto.class)))
                .thenReturn(expectedResponse);

        OtpVerificationDto actualResponse = otpService.verifyOtp(mockUser, mockOtpVerivicationDto);

        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void testRegenerateOtp() {

        String expectedOtp = "1234";

        Mockito.when(otpServiceImpl.regenerateOtp(any(User.class)))
                .thenReturn(expectedOtp);

        String actualDto = otpService.regenerateOtp(mockUser);

        assertEquals(expectedOtp, actualDto);

    }

}
