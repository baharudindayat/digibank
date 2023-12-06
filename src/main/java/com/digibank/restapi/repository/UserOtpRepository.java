package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOTP, Long> {

    Optional<UserOTP> findByIdUser(User idUser);
    void deleteByCreatedAtBefore(LocalDateTime twoMinutesAgo);

    List<UserOTP> findByCreatedAtBefore(LocalDateTime twoMinutesAgo);
}

