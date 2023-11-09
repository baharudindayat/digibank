package com.digibank.restapi.model.entity;

import com.digibank.restapi.model.enums.JenisTransaksi;
import com.digibank.restapi.model.enums.TipeTransaksi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private long id;

    @ManyToOne
    @JoinColumn(name = "rekening_asal")
    private Rekening rekeningAsal;

    @ManyToOne
    @JoinColumn(name = "rekening_tujuan")
    private Rekening rekeningTujuan;

    @Column(nullable = false, unique = true)
    private Integer kodeBank;

    @Column(nullable = false)
    private Long Nominal;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp waktuTransaksi;

    @Column(nullable = false)
    private JenisTransaksi jenisTransaksi;

    @Column(nullable = false)
    private String catatan;

    @Column(nullable = false)

    private TipeTransaksi tipeTransaksi;

    @Column(nullable = false)
    private String totalTransaksi;

    @PrePersist
    private void prePersist() {
        waktuTransaksi = new Timestamp(System.currentTimeMillis());
    }

}
