package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.cif.CifDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.TypeRekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.TypeRekeningRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.CifService;
import com.digibank.restapi.utils.NoRekUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CifServiceImpl implements CifService {

    private final UserRepository userRepository;
    private final CifRepository repository;
    private final NoRekUtil noRekUtil;
    private final RekeningRepository rekeningRepository;
    private final TypeRekeningRepository typeRekeningRepository;

    @Override
    public String createCif(CifDto cifDto, long idUser, long idTipe) {

        Optional<User> user = Optional.ofNullable(userRepository.findById(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan")));

        Optional<TypeRekening> typeRekening = Optional.ofNullable(typeRekeningRepository.findById(idTipe)
                .orElseThrow(() -> new ResponseBadRequestException("Tipe rekening tidak ditemukan")));

        CIF cif = new CIF();
        cif.setNik(cifDto.getNik());
        cif.setAlamat(cifDto.getAlamat());
        cif.setPekerjaan(cifDto.getPekerjaan());
        cif.setNamaLengkap(cifDto.getNamaLengkap());
        cif.setPenghasilan(cifDto.getPenghasilan());
        cif.setIdUsers(user.get());
        repository.save(cif);

        Optional<CIF> idCif = Optional.ofNullable(repository.findByNik(cifDto.getNik())
                .orElseThrow(() -> new ResponseUnauthorizationException("NIK tidak ditemukan")));

        String noRekening = noRekUtil.generateRekening();
        Rekening rekening = new Rekening();
        rekening.setNoRekening(Long.parseLong(noRekening));
        if(idCif.isPresent()){
            throw new ResponseBadRequestException("Rekenig Anda salah");
        }
        rekening.setIdCif(idCif.get());
        rekening.setTipeRekening(typeRekening.get());

        int typeValue = (int) typeRekening.get().getIdTipe();

        if (typeValue == 1) {
            rekening.setSaldo(50000.0);
        } else if (typeValue == 2) {
            rekening.setSaldo(100000.0);
        }else if(typeValue == 3){
            rekening.setSaldo(1000000.0);
        }

        rekeningRepository.save(rekening);
        return noRekening;
    }
}
