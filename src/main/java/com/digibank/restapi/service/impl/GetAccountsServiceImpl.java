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
import com.digibank.restapi.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAccountsServiceImpl implements GetAccountsService {


    private final CifRepository cifRepository;
    private final UserRepository userRepository;
    private final RekeningRepository rekeningRepository;
    private final JwtService jwtService;

    @Override
    public GetAccountsDto getAccounts(String token) {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
        CIF cif = cifRepository.findByidUsers(user)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
        GetAccountsDto getAccountsDto = new GetAccountsDto();
        getAccountsDto.setNik(cif.getNik());
        getAccountsDto.setEmail(user.getEmail());
        getAccountsDto.setRekening(getAllRekening(cif));
        return getAccountsDto;
    }

    public List<Rekening> getAllRekening(CIF cif) {
        return rekeningRepository.findByidCif(cif);
    }
}