package com.digibank.restapi.mapper;

import com.digibank.restapi.dto.TransferDto;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.Transaksi;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    public TransferDto toDto (Transaksi transaksi, Users users, Rekening rekening) {
        TransferDto transferDto = new TransferDto();
        transferDto.setId(transaksi.getId());
        transferDto.setNominal(transaksi.getNominal());
        transferDto.setCatatan(transaksi.getCatatan());
        transferDto.setMpin(users.getMpin());
        transferDto.setNoRekeningTujuan(rekening.getNo_rekening());
        transferDto.setNoRekeningSumber(rekening.getNo_rekening());
        return  transferDto;
    }
}
