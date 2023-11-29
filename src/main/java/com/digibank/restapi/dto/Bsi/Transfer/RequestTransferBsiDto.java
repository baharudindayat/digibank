package com.digibank.restapi.dto.Bsi.Transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransferBsiDto {

    private long noRekeningBsi;

    private long noRekeningDigibank;

    private Double nominal;

    private String catatan;

}
