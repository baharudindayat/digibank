package com.digibank.restapi.digibank.service;

import com.digibank.restapi.digibank.dto.tipe_rekeningDto;
import com.digibank.restapi.digibank.entity.tipe_rekening;
import com.digibank.restapi.digibank.repository.tipe_rekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class tipe_rekeningService {
    @Autowired
    private tipe_rekeningRepository tipeRekeningRepository;

    public List<tipe_rekeningDto> getAllTipeRekening() {
        List<tipe_rekening> tipeRekenings = tipeRekeningRepository.findAll();
        return tipeRekenings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private tipe_rekeningDto convertToDto(tipe_rekening tipeRekening) {
        tipe_rekeningDto dto = new tipe_rekeningDto();
        dto.setId_type(tipeRekening.getId_type());
        dto.setNama_type(tipeRekening.getNama_type());
        dto.setLimit_transfer(tipeRekening.getLimit_transfer());
        return dto;
    }
}
