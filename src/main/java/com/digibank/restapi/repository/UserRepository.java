package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByStatusUserAndActive(AccountStatus statusUser, Boolean active);
}
