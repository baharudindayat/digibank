package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.User;
import com.digibank.restapi.model.entity.UserOtp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOtpRepository extends CrudRepository<UserOtp, Long> {

    Optional<UserOtp> findByIdUser_IdUser(Long id_user);

    Optional<UserOtp> findByOtp(String otp);
}
