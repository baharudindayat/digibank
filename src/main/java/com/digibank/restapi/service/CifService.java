package com.digibank.restapi.service;

import com.digibank.restapi.dto.cif.CifDto;

public interface CifService {
    String createCif(CifDto cifDto, long idUser, long idTipe);

}
