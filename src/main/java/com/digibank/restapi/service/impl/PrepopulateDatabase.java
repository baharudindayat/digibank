//package com.digibank.restapi.service.impl;
//
//import com.digibank.restapi.model.Ktp;
//import com.digibank.restapi.repository.KtpRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class PrepopulateDatabase implements CommandLineRunner {
//
//    private KtpRepository ktpRepository;
//
//    @Autowired
//    public PrepopulateDatabase(KtpRepository ktpRepository) {
//        this.ktpRepository = ktpRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<Ktp> ktpList = new ArrayList<>();
//
//        Ktp ktp = new Ktp();
//        ktp.setNik("3324062206020003");
//        ktp.setProvinsi("Jawa Barat");
//        ktp.setKota("Bandung");
//        ktp.setNama("John Doe");
//        ktp.setTempat_lahir("Bandung");
//        ktp.setTanggal_lahir(new Date(1990, 1, 1));
//        ktp.setJenis_kelamin("Laki-laki");
//        ktp.setGolongan_darah("O");
//        ktp.setAlamat("Jl. Sudirman No. 1");
//        ktp.setRt("001");
//        ktp.setRw("001");
//        ktp.setKelurahan("Sukajadi");
//        ktp.setKecamatan("Sukajadi");
//        ktp.setAgama("Islam");
//        ktp.setStatus_perkawinan("Belum Kawin");
//        ktp.setPekerjaan("Karyawan");
//        ktp.setKewarganegaraan("Indonesia");
//        ktp.setBerlaku_hingga("Seumur Hidup");
//        ktp.setTanggal_perekaman(new Date(2023, 10, 30));
//        ktpList.add(ktp);
//
//        ktpRepository.saveAll(ktpList);
//    }
//}
