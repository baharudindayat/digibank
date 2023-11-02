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
    private long KodeBank;

    @Column(nullable = false, unique = true)
    private String NamaBank;
}
