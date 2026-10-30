package com.sbi.nasabah.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "nasabah")
public class Nasabah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama lengkap wajib diisi")
    @Size(max = 150, message = "Nama maksimal 150 karakter")
    @Column(name = "nama_lengkap", nullable = false, length = 150)
    private String namaLengkap;

    @NotBlank(message = "Alamat wajib diisi")
    @Size(max = 500, message = "Alamat maksimal 500 karakter")
    @Column(nullable = false, length = 500)
    private String alamat;

    @NotBlank(message = "Tempat lahir wajib diisi")
    @Size(max = 100, message = "Tempat lahir maksimal 100 karakter")
    @Column(name = "tempat_lahir", nullable = false, length = 100)
    private String tempatLahir;

    @NotNull(message = "Tanggal lahir wajib diisi")
    @PastOrPresent(message = "Tanggal lahir tidak boleh di masa depan")
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @NotBlank(message = "Nomor KTP wajib diisi")
    @Pattern(regexp = "\\d{16}", message = "Nomor KTP harus 16 digit")
    @Column(name = "nomor_ktp", nullable = false, length = 16)
    private String nomorKtp;

    @NotBlank(message = "Nomor handphone wajib diisi")
    @Pattern(regexp = "^628[\\d]{7,12}$", message = "Nomor handphone harus menggunakan format 628")
    @Column(name = "nomor_handphone", nullable = false, length = 15)
    private String nomorHandphone;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;

    public Nasabah() {
        // table nasabah
    }

    public Long getId() {
        return id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNomorKtp() {
        return nomorKtp;
    }

    public void setNomorKtp(String nomorKtp) {
        this.nomorKtp = nomorKtp;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHandphone) {
        this.nomorHandphone = nomorHandphone;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
