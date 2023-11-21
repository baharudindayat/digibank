package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.GetAccountsDto;
import com.digibank.restapi.dto.login.LoginResDto;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.repository.CifRepository;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.repository.UserRepository;
import com.digibank.restapi.service.GetAccountsService;
import com.digibank.restapi.service.UserService;
import com.digibank.restapi.utils.TokenDecoder;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccountsServiceImpl implements GetAccountsService {


    @Value("${token.login.key}")
    private String secretKey;

    @Autowired
    CifRepository cifRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RekeningRepository rekeningRepository;
    @Autowired
    UserService userService;


    @Override
    public GetAccountsDto getAccounts(String token) {
        Claims claims = TokenDecoder.decodeToken(token, secretKey);
        String email = claims.getSubject();
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
