package com.example.demo.hospital.record;

import com.example.demo.hospital.entity.Hospital;

public record ResponseRecord(Long id, String FCLTY_NM, String ADRES, String TELNO) {
    public static ResponseRecord fromEntity(Hospital entity) {
        return new ResponseRecord(
                entity.getId(),
                entity.getFCLTY_NM(),
                entity.getADRES(),
                entity.getTELNO()
        );
    }
}