package com.digibank.restapi.service;

import com.digibank.restapi.dto.CreateMpinDto;

public interface CreateMpinService {

    CreateMpinDto createMpin(Long idUser, CreateMpinDto createMpinDto);
}
