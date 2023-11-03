package com.digibank.restapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

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

    @Column(name = "id_user",nullable = false, unique = true)
    private String id_user;

    @Column(name = "nik",nullable = false, unique = true)
    private String nik;

    @Column(name = "nama_lengkap",nullable = false)
    private String nama_lengkap;

    @Column(name = "alamat",nullable = false)
    private String alamat;

    @Column(name = "pekerjaan",nullable = false)
    private String pekerjaan;

    @Column(name = "penghasilan",nullable = false)
    private String penghasilan;

    @Column(name = "created_cif",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created_cif;

    @OneToOne(mappedBy = "cif")
    private Users users;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipe_rekening")
    private Set<Rekening> cif;
}