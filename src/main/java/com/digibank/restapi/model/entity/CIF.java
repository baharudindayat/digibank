package com.digibank.restapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
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

    @Column(name = "nik",nullable = false, unique = true)
    private String nik;

    @Column(name = "nama_lengkap",nullable = false)
    private String namaLengkap;

    @Column(name = "alamat",nullable = false)
    private String alamat;

    @Column(name = "pekerjaan",nullable = false)
    private String pekerjaan;

    @Column(name = "penghasilan",nullable = false)
    private String penghasilan;

    @Column(name = "created_at",nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User idUser;


}