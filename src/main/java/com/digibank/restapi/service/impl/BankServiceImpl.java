package com.digibank.restapi.service.impl;

import com.digibank.restapi.model.entity.Bank;
import com.digibank.restapi.repository.BankRepository;
import com.digibank.restapi.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    @Override
    public List<Bank> getAllBank() {
        return bankRepository.findAll();
    }
}
