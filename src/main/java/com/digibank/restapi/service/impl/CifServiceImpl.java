package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.exception.FailedException;
import com.digibank.restapi.mapper.AutoCifMapper;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.utils.NoRekUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CifServiceImpl implements CifService {

    private  final UserRepository userRepository;
    private final CifRepository repository;
    private final NoRekUtil noRekUtil;
    private final RekeningRepository rekeningRepository;

    @Override
    public String createCif(CifDto cifDto) {

        Optional<User> idUser = Optional.ofNullable(userRepository.findById(cifDto.getIdUser().getIdUser())
                .orElseThrow(() -> new FailedException("User tidak ditemukan")));
//        CIF cif = AutoCifMapper.MAPPER.mapToCif(cifDto);
        CIF cif = new CIF();
        cif.setNik(cifDto.getNik());
        cif.setAlamat(cifDto.getAlamat());
        cif.setPekerjaan(cifDto.getPekerjaan());
        cif.setNamaLengkap(cifDto.getNamaLengkap());
        cif.setPenghasilan(cifDto.getPenghasilan());
        cif.setIdUsers(idUser.get());
        repository.save(cif);
        Optional<CIF> idCif = repository.findByNik(cifDto.getNik());
        String noRekening = noRekUtil.generateRekening();
        Rekening rekening = new Rekening();
        rekening.setNoRekening(Long.parseLong(noRekening));
        rekening.setIdCif(idCif.get());
        rekening.setTipeRekening(cifDto.getIdTipe());
        rekening.setSaldo(0.0);
        rekeningRepository.save(rekening);
        return noRekening;
    }
}
