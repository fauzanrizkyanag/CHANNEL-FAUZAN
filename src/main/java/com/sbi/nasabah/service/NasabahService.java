package com.sbi.nasabah.service;

import com.sbi.nasabah.dto.NasabahRequest;
import com.sbi.nasabah.entity.Nasabah;
import com.sbi.nasabah.repository.NasabahRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NasabahService {

    private final NasabahRepository nasabahRepository;

    public NasabahService(NasabahRepository nasabahRepository) {
        this.nasabahRepository = nasabahRepository;
    }

    @Transactional
    public Nasabah create(NasabahRequest request) {
        if (nasabahRepository.existsByNomorKtpAndDeletedFalse(request.nomorKtp())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Nomor KTP sudah terdaftar");
        }

        Nasabah nasabah = new Nasabah();
        nasabah.setNamaLengkap(request.namaLengkap().trim());
        nasabah.setAlamat(request.alamat().trim());
        nasabah.setTempatLahir(request.tempatLahir().trim());
        nasabah.setTanggalLahir(request.tanggalLahir());
        nasabah.setNomorKtp(request.nomorKtp());
        nasabah.setNomorHandphone(request.nomorHandphone());
        nasabah.setDeleted(false);

        return nasabahRepository.save(nasabah);
    }

    @Transactional(readOnly = true)
    public Nasabah findByNomorKtp(String nomorKtp) {
        return nasabahRepository.findByNomorKtpAndDeletedFalse(nomorKtp)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nasabah tidak ditemukan"));
    }

    @Transactional(readOnly = true)
    public List<Nasabah> findAll() {
        return nasabahRepository.findAllByDeletedFalse();
    }

    @Transactional
    public Nasabah update(String nomorKtp, NasabahRequest request) {
        Nasabah nasabah = findByNomorKtp(nomorKtp);

        nasabah.setNamaLengkap(request.namaLengkap().trim());
        nasabah.setAlamat(request.alamat().trim());
        nasabah.setTempatLahir(request.tempatLahir().trim());
        nasabah.setTanggalLahir(request.tanggalLahir());
        nasabah.setNomorHandphone(request.nomorHandphone());

        return nasabahRepository.save(nasabah);
    }

    @Transactional
    public void delete(String nomorKtp) {
        Nasabah nasabah = findByNomorKtp(nomorKtp);
        nasabah.setDeleted(true);
        nasabahRepository.save(nasabah);
    }
}
