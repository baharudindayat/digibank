package com.digibank.restapi.service;

import com.digibank.restapi.dto.cif.CifDto;
import com.digibank.restapi.dto.confirmRekening.ConfirmRekeningReqDto;

public interface UserCifService {

    String createUserRekening(ConfirmRekeningReqDto confirmRekeningReqDto, long idUser);
}
