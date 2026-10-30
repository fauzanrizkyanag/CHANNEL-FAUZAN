package com.sbi.nasabah.controller;

import com.sbi.nasabah.dto.NasabahRequest;
import com.sbi.nasabah.dto.NasabahResponse;
import com.sbi.nasabah.entity.Nasabah;
import com.sbi.nasabah.service.NasabahService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {

    private final NasabahService nasabahService;

    public NasabahController(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

    @PostMapping
    public ResponseEntity<NasabahResponse> create(
            @Valid @RequestBody NasabahRequest request) {
        Nasabah nasabah = nasabahService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(NasabahResponse.from(nasabah));
    }

    @GetMapping("/{nomorKtp}")
    public ResponseEntity<NasabahResponse> findByNomorKtp(
            @PathVariable String nomorKtp) {
        return ResponseEntity.ok(
                NasabahResponse.from(nasabahService.findByNomorKtp(nomorKtp)));
    }

    @GetMapping
    public ResponseEntity<List<NasabahResponse>> findAll() {
        List<NasabahResponse> response = nasabahService.findAll()
                .stream()
                .map(NasabahResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{nomorKtp}")
    public ResponseEntity<NasabahResponse> update(
            @PathVariable String nomorKtp,
            @Valid @RequestBody NasabahRequest request) {
        return ResponseEntity.ok(
                NasabahResponse.from(
                        nasabahService.update(nomorKtp, request)));
    }

    @DeleteMapping("/{nomorKtp}")
    public ResponseEntity<Void> delete(
            @PathVariable String nomorKtp) {
        nasabahService.delete(nomorKtp);
        return ResponseEntity.noContent().build();
    }
}
