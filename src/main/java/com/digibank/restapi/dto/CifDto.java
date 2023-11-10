package com.digibank.restapi.dto;


import com.digibank.restapi.model.entity.TypeRekening;
import com.digibank.restapi.model.entity.User;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CifDto {

    private  String nik;

    private  String namaLengkap;

    private  String alamat;

    private  String pekerjaan;

    private  String penghasilan;

    private User idUsers;

    private TypeRekening idTipe;

}
