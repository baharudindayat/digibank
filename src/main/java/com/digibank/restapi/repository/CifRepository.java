package com.digibank.restapi.repository;

import com.digibank.restapi.model.entity.CIF;
import com.digibank.restapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CifRepository extends JpaRepository<CIF, Long> {

    Optional<CIF> findByNik(String nik);

    Optional<CIF> findByidUsers(User idUsers);

}
