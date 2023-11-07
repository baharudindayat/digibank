package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    List<UserOtp> findByCreatedAtBefore(LocalDateTime dateTime);

    void deleteByCreatedAtBefore(LocalDateTime twoMinutesAgo);
}

