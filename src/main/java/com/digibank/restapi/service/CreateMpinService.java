package com.digibank.restapi.service;

import com.digibank.restapi.dto.mpin.CreateMpinDto;

public interface CreateMpinService {

    CreateMpinDto createMpin(Long idUser, CreateMpinDto createMpinDto);
    CreateMpinDto confirmMpin(Long idUser, CreateMpinDto createMpinDto);

}
