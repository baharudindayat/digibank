package com.digibank.restapi.dto.response.transaction.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RekeningDto {

    @JsonProperty("no_rekening")
    private String noRekening;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("nama_bank")
    private String namaBank;
}
