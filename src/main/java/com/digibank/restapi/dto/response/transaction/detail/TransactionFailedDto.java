package com.digibank.restapi.dto.response.transaction.detail;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionFailedDto {
    private Boolean error;
    private String message;
}
