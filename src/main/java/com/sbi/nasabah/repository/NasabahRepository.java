package com.sbi.nasabah.repository;

import com.sbi.nasabah.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {

    Optional<Nasabah> findByNomorKtpAndDeletedFalse(String nomorKtp);

    boolean existsByNomorKtpAndDeletedFalse(String nomorKtp);

    List<Nasabah> findAllByDeletedFalse();

}
