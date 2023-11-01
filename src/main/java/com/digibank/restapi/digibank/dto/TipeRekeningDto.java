package com.digibank.restapi.digibank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipeRekeningDto {
    private Integer id_type;
    private String nama_type;
    private String limit_transfer;
}
