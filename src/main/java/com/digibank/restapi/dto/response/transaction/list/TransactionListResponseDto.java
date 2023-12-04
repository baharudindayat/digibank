package com.digibank.restapi.dto.response.transaction.list;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionListResponseDto {
    private boolean error;
    private String message;
    private List<TransactionDto> transactions;
}
