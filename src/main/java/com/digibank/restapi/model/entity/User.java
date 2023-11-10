package com.digibank.restapi.model.entity;

import com.digibank.restapi.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUsers;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column(name = "status_user")
    private AccountStatus statusUser;

    private String mpin;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "count_blocked_mpin")
    private Integer countBlockedMpin;

    private Boolean active;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public void setOtp(String otp) {
    }

    public void setCretaedOtp(LocalDateTime now) {
    }

    public Object getOtp() {
        return null;
    }

    public Temporal getCretaedOtp() {
        return null;
    }
}