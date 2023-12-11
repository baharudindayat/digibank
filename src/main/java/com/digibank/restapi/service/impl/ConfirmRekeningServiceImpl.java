package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningResDto;
import com.digibank.restapi.exception.ResponseBadRequestException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.service.ConfirmRekeningService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmRekeningServiceImpl implements ConfirmRekeningService {
    private RekeningRepository rekeningRepository;
    private CifRepository cifRepository;
    @Override
    public ConfirmRekeningResDto confirmRekening(ConfirmRekeningReqDto confirmRekeningReqDto) {

        Optional<Rekening> rekening = Optional.ofNullable(rekeningRepository.findById(Long.valueOf(confirmRekeningReqDto.getNoRekening()))
                .orElseThrow(() -> new ResponseBadRequestException("Maaf! Nomor Rekening Tidak Terdaftar Silahkan Daftar Rekening.")));

        Optional<CIF> cif = Optional.ofNullable(cifRepository.findById(rekening.get().getIdCif().getId_cif())
                .orElseThrow(() -> new ResponseBadRequestException("Cif belum terdaftar")));

        if(cif.get().getIdUsers() != null) {
            throw new ResponseBadRequestException("Nomor rekening tidak tersedia");
        }

        ConfirmRekeningResDto confirmRekeningResDto = new ConfirmRekeningResDto();
        confirmRekeningResDto.setNamaLengkap(cif.get().getNamaLengkap());
        confirmRekeningResDto.setNik(cif.get().getNik());
        return confirmRekeningResDto;
    }
}
