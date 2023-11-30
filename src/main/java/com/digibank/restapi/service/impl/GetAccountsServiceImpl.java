package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.getAccount.GetAccountsDto;
import com.digibank.restapi.dto.getAccount.ResponseAccount;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GetAccountsServiceImpl implements GetAccountsService {


    private final CifRepository cifRepository;
    private final UserRepository userRepository;
    private final RekeningRepository rekeningRepository;
    private final JwtService jwtService;
    private List<ResponseAccount> responseAccounts;

    @Override
    public GetAccountsDto getAccounts(String token) {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
        CIF cif = cifRepository.findByidUsers(user)
                .orElseThrow(() -> new ResponseUnauthorizationException("User tidak ditemukan"));
        GetAccountsDto getAccountsDto = new GetAccountsDto();
        getAccountsDto.setName(cif.getNamaLengkap());
        getAccountsDto.setNik(cif.getNik());
        getAccountsDto.setEmail(user.getEmail());
        getAccountsDto.setAccounts(getAllRekening(cif));
        return getAccountsDto;
    }

    public List<ResponseAccount> getAllRekening(CIF cif) {
        List<Rekening> rekeningList = rekeningRepository.findByidCif(cif);
        responseAccounts.clear();
        for (Rekening rekening : rekeningList) {
            ResponseAccount responseAccount = new ResponseAccount();
            responseAccount.setRekening(String.valueOf(rekening.getNoRekening()));
            responseAccount.setSaldo(rekening.getSaldo());
            responseAccount.setTypeRekening(rekening.getTipeRekening());
            responseAccounts.add(responseAccount);
        }
        return responseAccounts;
    }
}
