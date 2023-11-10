package com.digibank.restapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_bank")
public class BankUser {
    @Id
    @GenericGenerator(name = "rekening-generator", type = com.digibank.restapi.utils.transfer.NoRekeningGenerator.class)
    @GeneratedValue(generator = "rekening-generator",strategy = GenerationType.IDENTITY)
    private String noRekening;

    private String nama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_bank")
    private Bank bank;
}
