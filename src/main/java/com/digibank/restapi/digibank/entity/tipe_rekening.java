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
    private String nama_type;
    private String limit_transfer;

    public tipe_rekening() {
    }

    public tipe_rekening(String nama_type, String limit_transfer) {
        this.nama_type = nama_type;
        this.limit_transfer = limit_transfer;
    }
}
