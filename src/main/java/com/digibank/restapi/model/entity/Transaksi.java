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
    private Rekening rekening_asal;

    @ManyToOne
    @JoinColumn(name = "rekening_tujuan")
    private Rekening rekening_tujuan;

    @Column(nullable = false, unique = true)
    private Integer kode_bank;

    @Column(nullable = false)
    private Long Nominal;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp waktu_transaksi;

    @Column(nullable = false)
    private JenisTransaksi jenis_transaksi;

    @Column(nullable = false)
    private String catatan;

    @Column(nullable = false)
    private TipeTransaksi tipe_transaksi;

    @Column(nullable = false)
    private String total_transaksi;

    @PrePersist
    private void prePersist() {
        waktu_transaksi = new Timestamp(System.currentTimeMillis());
    }

}
