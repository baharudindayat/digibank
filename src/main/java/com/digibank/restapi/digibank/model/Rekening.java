package com.digibank.restapi.digibank.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rekening", schema = "public")
public class Rekening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no_rekening;
    @Column(nullable = false, unique = true)
    private String saldo;
    private LocalDateTime created_at;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private TipeRekening tipe_rekening;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cif")
    private CIF cif;
}
