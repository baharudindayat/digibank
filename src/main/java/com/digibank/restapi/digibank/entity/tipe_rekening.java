package com.digibank.restapi.digibank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tipe_rekening", schema = "public")
public class tipe_rekening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_type;
    @Column(nullable = false, unique = true)
    private String nik;
    private String type;
    private String limit_transfer;
}
