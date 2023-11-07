package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.otp.OtpDto;
import com.digibank.restapi.dto.otp.OtpVerificationDto;
import com.digibank.restapi.exception.OtpException.OtpFailedException;
import com.digibank.restapi.mapper.UserMapper;
import com.digibank.restapi.mapper.UserOtpMapper;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOtp;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private OtpUtil otpUtil;
    private EmailUtil emailUtil;
    private UserRepository userRepository;
    private UserOtpRepository userOtpRepository;

    @Override
    public OtpDto register(OtpDto otpDto) {
        // Cari apakah pengguna dengan email tersebut sudah ada
        User existingUser = userRepository.findByEmail(otpDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new OtpFailedException("Email Sudah Terdaftar");
        }

        // Generate OTP
        String otp = otpUtil.generateOtp();

        try {
            // Kirim email OTP
            emailUtil.sendOtpEmail(otpDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new OtpFailedException("Unable to send OTP. Please try again.");
        }

        // Simpan pengguna ke dalam database
        User user = UserMapper.MAPPER.mapToUser(otpDto);
        User savedUser = userRepository.save(user);

        // Simpan OTP ke dalam tabel UserOtp
        UserOtp userOtp = UserOtpMapper.MAPPER.mapToUserOtp(otpDto);
        userOtp.setOtp(otp);
        userOtp.setIdUser(savedUser);
        userOtp.setCreatedAt(new Date()); // Set createdAt ke waktu sekarang
        userOtpRepository.save(userOtp);

        // Mengembalikan data DTO
        OtpDto responseDto = UserMapper.MAPPER.mapToOtpDto(savedUser);
        responseDto.setId_user(savedUser.getIdUser());

        return responseDto;
    }

    @Override
    public OtpVerificationDto verifyOtp(Long id_otp, OtpDto otpDto) {
        // Cari UserOtp berdasarkan id_otp
        UserOtp userOtp = userOtpRepository.findById(id_otp)
                .orElseThrow(() -> new OtpFailedException("Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi."));

        // Pastikan id_user cocok dengan yang ditemukan
        Long id_user = otpDto.getId_user();
        if (id_user != null && userOtp.getIdUser().getIdUser() != id_user) {
            throw new OtpFailedException("Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.");
        }

        // Periksa apakah waktu OTP masih berlaku (2 menit)
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime createdAt = userOtp.getCreatedAt();
        long diffInSeconds = Duration.between(createdAt, currentTime).getSeconds();
        if (diffInSeconds < (2 * 60)) {
            User user = userOtp.getIdUser();
            Boolean active = user.getActive();

            if (active != null && active) {
                // Akun sudah terverifikasi, kirim respons kesalahan
                throw new OtpFailedException("Akun sudah terverifikasi.");
            }

            // Tandai akun sebagai terverifikasi
            if (active == null) {
                active = true;
            }
            user.setActive(active);
            userRepository.save(user);

            // Hapus kolom id_otp dari entitas UserOtp
            userOtpRepository.delete(userOtp);

            // Mengembalikan respons sukses
            return new OtpVerificationDto("OTP Terverifikasi");
        } else {
            // Verifikasi gagal, hapus entitas UserOtp
            userOtpRepository.delete(userOtp);

            // Mengembalikan respons dengan status 400 dan pesan error
            throw new OtpFailedException("Kode OTP yang dimasukkan tidak valid. Silahkan coba lagi.");
        }
    }

    // Metode ini akan dijalankan setiap 2 menit
    @Transactional
    @Scheduled(fixedRate = 120000)
    public void deleteExpiredUserOtp() {
        LocalDateTime twoMinutesAgo = LocalDateTime.now().minusMinutes(2);
        userOtpRepository.deleteByCreatedAtBefore(twoMinutesAgo);
    }

        //    @Override
    //    public String regenerateOtp(String email) {
    //        User user = userRepository.findByEmail(email)
    //                .orElseThrow(() -> new OtpFailedException("Email tidak dapat ditemukan"));
    //        String otp = otpUtil.generateOtp();
    //        try {
    //            emailUtil.sendOtpEmail(email, otp);
    //        } catch (MessagingException e) {
    //            throw new OtpFailedException("Tidak dapat mengirim otp, silakan coba lagi");
    //        }
    //        user.setOtp(otp);
    //        user.setCretaedOtp(LocalDateTime.now());
    //        userRepository.save(user);
    //        return "OTP Terkirim Kembali";
    //}

}