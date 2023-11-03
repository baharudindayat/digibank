package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.TransferDto;
import com.digibank.restapi.mapper.TransferMapper;
import com.digibank.restapi.repository.TransferRepository;
import com.digibank.restapi.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private TransferRepository transferRepository;
    private TransferMapper transferMapper;


    @Override
    public TransferDto createTransfer(TransferDto transferDto) {
        return null;
    }
}
