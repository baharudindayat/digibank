package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.GetAccountsDto;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.GetAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAccountsServiceImpl implements GetAccountsService {

    CifRepository cifRepository;
    UserRepository userRepository;
    RekeningRepository rekeningRepository;

    @Override
    public GetAccountsDto getAccounts(User idUser) {

        CIF cif = cifRepository.findByidUsers(idUser)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
        User user = userRepository.findById(idUser.getIdUser())
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
//        Rekening rekening = rekeningRepository.findByidCif(cif)
//                .orElseThrow(() -> new ResponseUnauthorizationException("Rekening tidak ditemukan"));
        GetAccountsDto getAccountsDto = new GetAccountsDto();
        getAccountsDto.setNik(cif.getNik());
        getAccountsDto.setEmail(user.getEmail());
        getAccountsDto.setRekening(getAllRekening(cif));

        return getAccountsDto;
    }

    public List<Rekening> getAllRekening(CIF cif) {
//        RekeningResponse rekeningResponse = new RekeningResponse();
        List<Rekening> rekening = rekeningRepository.findByidCif(cif);

//        @NotNull List<Rekening> optionalRekening = (List<Rekening>) rekening.get();
        return rekening;
    }
}
