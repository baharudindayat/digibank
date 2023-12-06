package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpResponseDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseRequestTimeoutException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.mapper.UserMapper;
import com.digibank.restapi.mapper.UserOtpMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOTP;
import com.digibank.restapi.model.enums.AccountStatus;
import com.digibank.restapi.repository.UserOtpRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.OtpService;
import com.digibank.restapi.utils.EmailUtil;
import com.digibank.restapi.utils.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private OtpUtil otpUtil;
    private EmailUtil emailUtil;
    private UserRepository userRepository;
    private UserOtpRepository userOtpRepository;

    @Override
    public OtpResponseDto register(OtpDto otpDto) {
        User existingUser = userRepository.findByEmail(otpDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new ResponseBadRequestException("Email Sudah Terdaftar");
        }

        String otp = otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(otpDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new ResponseRequestTimeoutException("Gagal mengirim OTP, Silahkan Coba lagi");
        }

        User user = UserMapper.MAPPER.mapToUser(otpDto);
        user.setStatusUser(AccountStatus.INACTIVE);
        user.setCountBlockedMpin(0);
        User savedUser = userRepository.save(user);

        UserOTP userOtp = UserOtpMapper.MAPPER.mapToUserOtp(otpDto);
        userOtp.setOtp(otp);

        userOtp.setIdUser(savedUser);
        userOtp.setCreatedAt(new Date());
        userOtpRepository.save(userOtp);

        OtpResponseDto responseDto = UserMapper.MAPPER.mapToOtpDto(savedUser);
        responseDto.setIdUser(savedUser.getIdUser());

        return responseDto;
    }

    @Override
    public OtpVerificationDto verifyOtp(User idUser, OtpVerificationDto otpVerificationDto) {
        Optional<UserOTP> userOTP = Optional.ofNullable(userOtpRepository.findByIdUser(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukkan")));

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime createdAt = userOTP.get().getCreatedAt();
        long diffInSeconds = Duration.between(createdAt, currentTime).getSeconds();
        if(!Objects.equals(userOTP.get().getOtp(), otpVerificationDto.getOtp())) {
            throw  new ResponseBadRequestException("Kode OTP yang dimasukkan tidak valid");
        }
        if (diffInSeconds < (2 * 60)) {
            User user = userOTP.get().getIdUser();
            Boolean active = user.getActive();

            if (active != null && active) {
                throw new ResponseBadRequestException("Akun sudah terverifikasi");
            }

            if (active == null) {
                active = true;
            }

            user.setActive(active);
            user.setStatusUser(AccountStatus.ACTIVE);
            userRepository.save(user);
            userOtpRepository.delete(userOTP.get());

            return otpVerificationDto;
        } else {
            userOtpRepository.delete(userOTP.get());
            throw new ResponseBadRequestException("Kode OTP yang dimasukkan tidak valid");
        }
    }

    @Transactional
    @Scheduled(fixedRate = 120000)
    public void deleteExpiredUserOtp() {
        LocalDateTime twoMinutesAgo = LocalDateTime.now().minusMinutes(2);
        userOtpRepository.deleteByCreatedAtBefore(twoMinutesAgo);
    }

    @Override
    public String regenerateOtp(User idUser) {

        Optional<UserOTP> userOtp = Optional.ofNullable(userOtpRepository.findByIdUser(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan")));

        Optional<User> user = Optional.ofNullable(userRepository.findById(idUser.getIdUser())
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan")));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(user.get().getEmail(), otp);
        } catch (MessagingException e) {
            throw new ResponseRequestTimeoutException("Gagal mengirim OTP, Silahkan Coba lagi");
        }
        userOtp.get().setOtp(otp);
        userOtp.get().setCreatedAt(new Date());
        userOtpRepository.save(userOtp.get());
        return "OTP Terkirim Kembali";
    }

}