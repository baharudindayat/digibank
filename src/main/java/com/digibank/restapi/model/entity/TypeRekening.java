package com.digibank.restapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipe_rekening", schema = "public")
public class TypeRekening{

    @Id
    private Integer id_type;

    private String nama_type;

    private String limit_transfer;
}