package com.sbi.nasabah.dto;

import com.sbi.nasabah.entity.Nasabah;

import java.time.LocalDate;

public record NasabahResponse(
        Long id,
        String namaLengkap,
        String alamat,
        String tempatLahir,
        LocalDate tanggalLahir,
        String nomorKtp,
        String nomorHandphone) {
    public static NasabahResponse from(Nasabah nasabah) {
        return new NasabahResponse(
                nasabah.getId(),
                nasabah.getNamaLengkap(),
                nasabah.getAlamat(),
                nasabah.getTempatLahir(),
                nasabah.getTanggalLahir(),
                nasabah.getNomorKtp(),
                nasabah.getNomorHandphone());
    }
}
