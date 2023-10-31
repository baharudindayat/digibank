package com.digibank.restapi.User.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    public enum status_user{
        Terblokir,Active,Inactive
    }
    private status_user Status_User;
    private String mpin;

    //id_cif fk (belum dibuat)
    private LocalDateTime created_user;
    private Integer count_blocked_mpin;
    @Column(length = 4, nullable = false, unique = true)
    private String otp;
    private boolean active;
    private LocalDateTime created_otp;
}
