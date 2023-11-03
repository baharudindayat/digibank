package com.digibank.restapi.model.entity;

import com.digibank.restapi.model.enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

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

    private Boolean active;

    private LocalDateTime cretaedOtp;
}