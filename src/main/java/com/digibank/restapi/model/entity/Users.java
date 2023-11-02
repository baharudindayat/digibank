package com.digibank.restapi.model.entity;

import com.digibank.restapi.model.enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "ID should not be null")
    private long id_user;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private AccountStatus StatusUser;

    @Column(nullable = false)
    private String mpin;

    @PrimaryKeyJoinColumn(name = "id_cif")
    @OneToOne(fetch = FetchType.LAZY)
    private CIF Idcif;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdUser;

    @Column(nullable = false)
    private Integer CountBlockedMpin;

    @Column(nullable = false)
    private String Otp;

    @Column(nullable = false)
    private Integer active;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp cretaedOtp;
}
