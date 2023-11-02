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
@Table(name = "rekening", schema = "public")
public class Rekening {

    @Id
    @Column(nullable = false, unique = true)
    private long no_rekening;

    @Column(nullable = false)
    private String saldo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private TypeRekening tipe_rekening;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cif")
    private Set<CIF> cif;
}
