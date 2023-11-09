package com.digibank.restapi.service;

import com.digibank.restapi.dto.CifDto;
import com.digibank.restapi.dto.CifResponseDto;

public interface CifService {
    CifResponseDto createCif(CifDto cifDto);

}
