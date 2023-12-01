package com.digibank.restapi.service.impl;

import com.digibank.restapi.dto.deposito.DepositoReqDto;
import com.digibank.restapi.exception.ResponseUnauthorizationException;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.repository.RekeningRepository;
import com.digibank.restapi.service.DepositoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    private final RekeningRepository rekeningRepository;
    @Override
    public String addSaldo(DepositoReqDto depositoReqDto) {

        Optional<Rekening> rekening = Optional.ofNullable(rekeningRepository.findById(Long.valueOf(depositoReqDto.getNorek()))
                .orElseThrow(() -> new ResponseUnauthorizationException("No Rekening tidak terdaftar")));

        var totalSaldo = rekening.get().getSaldo() + depositoReqDto.getNominal();

        rekening.get().setSaldo(totalSaldo);

        return null;
    }
}
