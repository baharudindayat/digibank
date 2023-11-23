package com.digibank.restapi.service;

import com.digibank.restapi.dto.KtpDto;
import org.springframework.stereotype.Service;

@Service
public interface KtpService {

    KtpDto createKtp(KtpDto ktpDto);

    Object getKtpById(String id);


}
