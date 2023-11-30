package com.digibank.restapi.dto.dukcapil;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KtpDto {

    @Schema(
            description = "Dukcapil ID"
    )
    @NotEmpty(message = "Dukcapil ID should not be null or empty")
    private String nik;

    @Schema(
            description = "Dukcapil Provinsi"
    )
    @NotEmpty(message = "Dukcapil Provinsi should not be null or empty")
    private String provinsi;

    @Schema(
            description = "Dukcapil Kota"
    )
    @NotEmpty(message = "Dukcapil Kota should not be null or empty")
    private String kota;

    @Schema(
            description = "Dukcapil Nama"
    )
    @NotEmpty(message = "Dukcapil Nama should not be null or empty")
    private String nama;

    @Schema(
            description = "Dukcapil Tempat Lahir"
    )
    @NotEmpty(message = "Dukcapil Tempat Lahir should not be null or empty")
    private String tempat_lahir;

    @Schema(
            description = "Dukcapil Tempat Lahir"
    )
    @NotEmpty(message = "Dukcapil Tempat Lahir should not be null or empty")
    private Date tanggal_lahir;

    @Schema(
            description = "Dukcapil Jenis Kelamin"
    )
    @NotEmpty(message = "Dukcapil Jenis Kelamin should not be null or empty")
    private String jenis_kelamin;

    @Schema(
            description = "Dukcapil Golongan Darah"
    )
    @NotEmpty(message = "Dukcapil Golongan Darah should not be null or empty")
    private String golongan_darah;

    @Schema(
            description = "Dukcapil Alamat"
    )
    @NotEmpty(message = "Dukcapil Alamat should not be null or empty")
    private String alamat;

    @Schema(
            description = "Dukcapil RT"
    )
    @NotEmpty(message = "Dukcapil RT should not be null or empty")
    private String rt;

    @Schema(
            description = "Dukcapil RW"
    )
    @NotEmpty(message = "Dukcapil RW should not be null or empty")
    private String rw;

    @Schema(
            description = "Dukcapil Kelurahan"
    )
    @NotEmpty(message = "Dukcapil Kelurahan should not be null or empty")
    private String kelurahan;

    @Schema(
            description = "Dukcapil Kecamatan"
    )
    @NotEmpty(message = "Dukcapil Kecamatan should not be null or empty")
    private String kecamatan;

    @Schema(
            description = "Dukcapil Agama"
    )
    @NotEmpty(message = "Dukcapil Agama should not be null or empty")
    private String agama;

    @Schema(
            description = "Dukcapil Status Perkawinan"
    )
    @NotEmpty(message = "Dukcapil Status Perkawinan should not be null or empty")
    private String status_perkawinan;

    @Schema(
            description = "Dukcapil Pekerjaan"
    )
    @NotEmpty(message = "Dukcapil Pekerjaan should not be null or empty")
    private String pekerjaan;

    @Schema(
            description = "Dukcapil Kewarganegaraan"
    )
    @NotEmpty(message = "Dukcapil Kewarganegaraan should not be null or empty")
    private String kewarganegaraan;

    @Schema(
            description = "Dukcapil Berlaku Hingga"
    )
    @NotEmpty(message = "Dukcapil Berlaku Hingga should not be null or empty")
    private String berlaku_hingga;

    @Schema(
            description = "Dukcapil tanggal_perekaman"
    )
    @NotEmpty(message = "Dukcapil Foto should not be null or empty")
    private Date tanggal_perekaman;
}