package com.digibank.restapi.service;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpResponseDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseRequestTimeoutException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOTP;
import com.digibank.restapi.repository.UserOtpRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.impl.OtpServiceImpl;
import com.digibank.restapi.utils.EmailUtil;
import com.digibank.restapi.utils.OtpUtil;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OtpServiceTest {

    @InjectMocks
    private OtpServiceImpl otpService;

    @Mock
    private OtpUtil otpUtil;

    @Mock
    private EmailUtil emailUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserOtpRepository userOtpRepository;

    @Test
    void register_Success() throws MessagingException {

        OtpDto otpDto = new OtpDto();
        otpDto.setEmail("test@example.com");

        User savedUser = new User();
        savedUser.setIdUser(1L);

        UserOTP userOtp = new UserOTP();
        userOtp.setOtp("1234");

        Mockito.when(userRepository.findByEmail(otpDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(otpUtil.generateOtp()).thenReturn("1234");
        Mockito.doNothing().when(emailUtil).sendOtpEmail(Mockito.any(), Mockito.any());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);
        Mockito.when(userOtpRepository.save(Mockito.any(UserOTP.class))).thenReturn(userOtp);

        OtpResponseDto responseDto = otpService.register(otpDto);

        assert responseDto != null;
        assert responseDto.getIdUser().equals(1L);

        // Verify that sendOtpEmail was called once
        Mockito.verify(emailUtil, Mockito.times(1)).sendOtpEmail(Mockito.any(), Mockito.any());
    }

    @Test
    void register_EmailAlreadyExists_ThrowsBadRequestException() {
        // Arrange
        OtpDto otpDto = new OtpDto();
        otpDto.setEmail("test@example.com");

        User existingUser = new User();

        Mockito.when(userRepository.findByEmail(otpDto.getEmail())).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(ResponseBadRequestException.class, () -> otpService.register(otpDto));
    }

//    @Test
//    void verifyOtp_Success() {
//        // Arrange
//        User idUser = new User();
//        idUser.setIdUser(1L);
//        OtpVerificationDto otpVerificationDto = new OtpVerificationDto();
//        String generatedOtp = "1234";
//        otpVerificationDto.setOtp(generatedOtp);
//
//        UserOTP userOtp = new UserOTP();
//        userOtp.setOtp(generatedOtp);
//        userOtp.setCreatedAt(Date.from(LocalDateTime.now().minusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()));
//
//        Mockito.when(userOtpRepository.findByIdUser(idUser)).thenReturn(Optional.of(userOtp));
//        Mockito.when(otpUtil.generateOtp()).thenReturn(generatedOtp);
//
//        OtpVerificationDto resultDto = otpService.verifyOtp(idUser, otpVerificationDto);
//
//        assertNotNull(resultDto);
//        assertEquals(otpVerificationDto.getOtp(), resultDto.getOtp());
//    }


    @Test
    void verifyOtp_ExpiredOtp_ThrowsBadRequestException() {
        // Arrange
        User idUser = new User();
        idUser.setIdUser(1L);
        OtpVerificationDto otpVerificationDto = new OtpVerificationDto();
        otpVerificationDto.setOtp("1234");

        UserOTP userOtp = new UserOTP();
        userOtp.setOtp("1234");
        userOtp.setCreatedAt(Date.from(LocalDateTime.now().minusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()));

        Mockito.when(userOtpRepository.findByIdUser(idUser)).thenReturn(Optional.of(userOtp));
        Mockito.when(otpUtil.generateOtp()).thenReturn("1234");

        // Act & Assert
        assertThrows(ResponseBadRequestException.class, () -> otpService.verifyOtp(idUser, otpVerificationDto));
    }

    @Test
    void regenerateOtp_Success() {
        // Arrange
        User idUser = new User();
        idUser.setIdUser(1L);
        UserOTP userOtp = new UserOTP();
        userOtp.setOtp("5678");

        Mockito.when(userOtpRepository.findByIdUser(idUser)).thenReturn(Optional.of(userOtp));
        Mockito.when(userRepository.findById(idUser.getIdUser())).thenReturn(Optional.of(idUser));
        Mockito.when(otpUtil.generateOtp()).thenReturn("5678");
        try {
            Mockito.doNothing().when(emailUtil).sendOtpEmail(Mockito.any(), Mockito.any());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // Act
        String result = otpService.regenerateOtp(idUser);

        // Assert
        assertEquals("OTP Terkirim Kembali", result);
        assertEquals("5678", userOtp.getOtp()); // Make sure the userOtp is updated
    }

    @Test
    void regenerateOtp_UserNotFound_ThrowsUnauthorizationException() {
        // Arrange
        User idUser = new User();
        idUser.setIdUser(1L);

        Mockito.when(userOtpRepository.findByIdUser(idUser)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseUnauthorizationException.class, () -> otpService.regenerateOtp(idUser));
    }
}
