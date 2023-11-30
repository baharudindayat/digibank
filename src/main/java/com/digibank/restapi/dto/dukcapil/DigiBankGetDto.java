package com.digibank.restapi.dto.dukcapil;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DigiBankGetDto {

    @Schema(
            description = "Dukcapil ID"
    )
    @NotEmpty(message = "Dukcapil ID should not be null or empty")
    private String nik;

    @Schema(
            description = "Dukcapil Alamat"
    )
    @NotEmpty(message = "Dukcapil Alamat should not be null or empty")
    private String alamat;

    @Schema(
            description = "Dukcapil Nama"
    )
    @NotEmpty(message = "Dukcapil Nama should not be null or empty")
    private String nama;

    @Schema(
            description = "Dukcapil Pekerjaan"
    )
    @NotEmpty(message = "Dukcapil Pekerjaan should not be null or empty")
    private String pekerjaan;
}
