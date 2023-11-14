package com.digibank.restapi.dto.response;

import com.digibank.restapi.dto.response.transaction.detail.RekeningDto;
import com.digibank.restapi.dto.response.transaction.detail.TransactionDetailDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResposneDto {
    private Boolean error;
    private String message;
    private RekeningDto pengirim;
    private RekeningDto penerima;
    private TransactionDetailDto data;
}
