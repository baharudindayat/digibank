package com.digibank.restapi.digibank.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cif", schema = "public")
public class CIF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cif;
    @Column(nullable = false, unique = true)
    private String nik;
    private String nama_lengkap;
    private String alamat;
    private String pekerjaan;
    private String penghasilan;
    private LocalDateTime created_cif;
}
