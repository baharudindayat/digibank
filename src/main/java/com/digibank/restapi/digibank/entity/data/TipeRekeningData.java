package com.digibank.restapi.digibank.entity.data;

import com.digibank.restapi.digibank.entity.TipeRekening;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TipeRekeningData {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void initData() {
        // Cek apakah entri dengan ID yang akan dibuat sudah ada
        if (!isIdExists(1)) {
            TipeRekening silver = new TipeRekening("Silver", "5 Juta");
            entityManager.persist(silver);
        }

        if (!isIdExists(2)) {
            TipeRekening gold = new TipeRekening("Gold", "10 Juta");
            entityManager.persist(gold);
        }

        if (!isIdExists(3)) {
            TipeRekening platinum = new TipeRekening("Platinum", "15 Juta");
            entityManager.persist(platinum);
        }
    }

    // Fungsi untuk memeriksa apakah ID sudah ada
    private boolean isIdExists(Integer id) {
        String queryString = "SELECT id_type FROM tipe_rekening WHERE id_type = ?";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter(1, id);

        try {
            query.getSingleResult();
            return true; // ID sudah ada
        } catch (NoResultException e) {
            return false; // ID belum ada
        }
    }
}