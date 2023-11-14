package com.digibank.restapi.service;

import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningResDto;

public interface ConfirmRekeningService {

    ConfirmRekeningResDto confirmRekening(ConfirmRekeningReqDto confirmRekeningReqDto);
}
