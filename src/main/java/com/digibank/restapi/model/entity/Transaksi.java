package com.digibank.restapi.model.entity;

import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kode_transaksi")
    private long kodeTransaksi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rekening_asal")
    @JsonIgnore
    private Rekening rekeningAsal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rekening_tujuan")
    @JsonIgnore
    private Rekening rekeningTujuan;

    @ManyToOne
    @JoinColumn(name = "kode_bank")
    private Bank bank;

    @Column(nullable = false)
    private Double Nominal;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp waktuTransaksi;

    @Column(name="jenis_transaksi", nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisTransaksi jenisTransaksi;

    @Column(nullable = false)
    private String catatan;


    @Column(name="tipe_transaksi",nullable = false)
    @Enumerated(EnumType.STRING)
    private TipeTransaksi tipeTransaksi;

    @Column(nullable = false)
    private Double totalTransaksi;
}

