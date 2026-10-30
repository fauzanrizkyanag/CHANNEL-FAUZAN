package com.sbi.nasabah.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NasabahRequest(

        @NotBlank(message = "Nama lengkap wajib diisi") @Size(max = 150, message = "Nama maksimal 150 karakter") String namaLengkap,

        @NotBlank(message = "Alamat wajib diisi") @Size(max = 500, message = "Alamat maksimal 500 karakter") String alamat,

        @NotBlank(message = "Tempat lahir wajib diisi") @Size(max = 100, message = "Tempat lahir maksimal 100 karakter") String tempatLahir,

        @NotNull(message = "Tanggal lahir wajib diisi") @PastOrPresent(message = "Tanggal lahir tidak boleh di masa depan") LocalDate tanggalLahir,

        @NotBlank(message = "Nomor KTP wajib diisi") @Pattern(regexp = "\\d{16}", message = "Nomor KTP harus 16 digit") String nomorKtp,

        @NotBlank(message = "Nomor handphone wajib diisi") @Pattern(regexp = "^628[\\d]{7,12}$", message = "Nomor handphone harus menggunakan format 628") String nomorHandphone) {
}
