package com.digibank.restapi.digibank.service;

import com.digibank.restapi.digibank.dto.TipeRekeningDto;
import com.digibank.restapi.digibank.entity.TipeRekening;
import com.digibank.restapi.digibank.repository.TipeRekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipeRekeningService {
    @Autowired
    private TipeRekeningRepository tipeRekeningRepository;

    public List<TipeRekeningDto> getAllTipeRekening() {
        List<TipeRekening> tipeRekenings = tipeRekeningRepository.findAll();
        return tipeRekenings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TipeRekeningDto convertToDto(TipeRekening tipeRekening) {
        TipeRekeningDto dto = new TipeRekeningDto();
        dto.setId_type(tipeRekening.getId_type());
        dto.setNama_type(tipeRekening.getNama_type());
        dto.setLimit_transfer(tipeRekening.getLimit_transfer());
        return dto;
    }
}
