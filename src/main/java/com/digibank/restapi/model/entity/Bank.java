package com.digibank.restapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "bank", schema = "public")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kode_bank")
    private long KodeBank;

    @Column(nullable = false, unique = true,name = "nama_bank")
    private String NamaBank;
}
