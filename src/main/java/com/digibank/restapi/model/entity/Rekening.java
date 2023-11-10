package com.digibank.restapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "rekening", schema = "public")
public class Rekening {

    @Id
    @Column(name = "no_rekening",nullable = false, unique = true)
    private long noRekening;

    @Column(nullable = false)
    private Double saldo;

    @Column(name = "created_at",nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipe_rekening")
    @JsonIgnore
    private TypeRekening tipeRekening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cif")
    @JsonIgnore
    private CIF idCif;

}
