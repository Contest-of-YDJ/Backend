package com.example.demo.hospital.dto;

import com.example.demo.hospital.entity.Hospital;
import lombok.Getter;

@Getter
public class ResponseDto {
    private Long id;
    private String FCLTY_NM;
    private String ADRES;
    private String TELNO;

    public ResponseDto(Hospital entity){
        this.id = entity.getId();
        this.FCLTY_NM = entity.getFCLTY_NM();
        this.ADRES = entity.getADRES();
        this.TELNO = entity.getTELNO();
    }
}