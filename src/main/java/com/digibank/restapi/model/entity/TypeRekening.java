package com.digibank.restapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tipe_rekening", schema = "public")
public class TypeRekening{

    @Id
    @Column(name = "id_tipe_rekening")
    private long idTipe;

    @Column(name = "nama_tipe", nullable = false)
    private String namaTipe;

    @Column(name = "limit_transfer", nullable = false)
    private String limitTransfer;
}