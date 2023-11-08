package com.digibank.restapi.dto;

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


    @Schema(
            description = "CIF NIK"
    )
    @NotEmpty(message = "NIK should not be null or empty")
    private  String nik;

    @Schema(
            description = "CIF Nama Lengkap"
    )
    @NotEmpty(message = "Nama Lengkap should not be null or empty")
    private  String namaLengkap;

    @Schema(
            description = "CIF Alamat"
    )
    @NotEmpty(message = "Alamat should not be null or empty")
    private  String alamat;

    @Schema(
            description = "CIF Pekerjaan"
    )
    @NotEmpty(message = "Pekerjaan should not be null or empty")
    private  String pekerjaan;

    @Schema(
            description = "CIF Penghasilan"
    )
    @NotEmpty(message = "Penghasilan should not be null or empty")
    private  String penghasilan;

    @Schema(
            description = "ID Users"
    )
    @NotEmpty(message = "ID Users should not be null or empty")
    private User idUsers;

}
