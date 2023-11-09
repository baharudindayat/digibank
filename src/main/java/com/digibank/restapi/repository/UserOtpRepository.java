package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserBank;
import com.digibank.restapi.model.entity.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOTP, Long> {
    List<UserOTP> findByCreatedAtBefore(LocalDateTime dateTime);

    Optional<UserOTP> findByIdUser(User idUser);
    void deleteByCreatedAtBefore(LocalDateTime twoMinutesAgo);
}

