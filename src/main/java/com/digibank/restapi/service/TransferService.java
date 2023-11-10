package com.digibank.restapi.service;


import com.digibank.restapi.dto.TransferDto;

public interface TransferService {
    TransferDto createTransfer(TransferDto transferDto);
