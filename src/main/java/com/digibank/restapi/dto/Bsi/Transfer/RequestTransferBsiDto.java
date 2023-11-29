package com.digibank.restapi.dto.Bsi.Transfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTransferBsiDto {

    private long noRekeningBsi;

    private long noRekeningDigibank;

    private Double nominal;

    private String catatan;

}
