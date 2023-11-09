package com.digibank.restapi.dto;

import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.Rekening;
import com.digibank.restapi.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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

}
