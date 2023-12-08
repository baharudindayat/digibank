package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.service.UserCifService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCifServiceImpl implements UserCifService {

    private RekeningRepository rekeningRepository;
    private CifRepository cifRepository;
    private UserRepository userRepository;

    @Override
    public String createUserRekening(ConfirmRekeningReqDto confirmRekeningReqDto, long idUser) {

//        Rekening rekening = rekeningRepository.findById(Long.valueOf(norek)).orElseThrow(
//                () -> new ResponseUnauthorizationException("No rekening tidak ditemukkan")
//        );
        Optional<Rekening> rekening = Optional.ofNullable(rekeningRepository.findById(Long.valueOf(confirmRekeningReqDto.getNoRekening()))
                .orElseThrow(() -> new ResponseBadRequestException("Maaf! Nomor Rekening Tidak Terdaftar Silahkan Daftar Rekening.")));

        CIF cif =  cifRepository.findById(rekening.get().getIdCif().getId_cif()).orElseThrow(
                () -> new ResponseUnauthorizationException("Cif tidak ditemukkan")
        );
        User user =  userRepository.findById(idUser).orElseThrow(
                () -> new ResponseUnauthorizationException("User tidak ditemukkan")
        );
        cif.setIdUsers(user);
        cifRepository.save(cif);
        return "User berhasil ditambahkan";
    }
}
