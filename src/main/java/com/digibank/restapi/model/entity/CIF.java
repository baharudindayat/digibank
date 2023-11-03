package com.digibank.restapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "cif", schema = "public")
public class CIF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id_cif;

    @Column(nullable = false, unique = true)
    private String nik;

    @Column(nullable = false)
    private String nama_lengkap;

    @Column(nullable = false)
    private String alamat;

    @Column(nullable = false)
    private String pekerjaan;

    @Column(nullable = false)
    private String penghasilan;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created_cif;
}