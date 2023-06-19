package com.example.demo.hospital.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String FCLTY_NM;

    @Column(length = 50)
    private String ADRES;

    @Column(length = 100)
    private String TELNO;

    @Builder
    public Hospital(String FCLTY_NM, String ADRES, String TELNO){
        this.FCLTY_NM = FCLTY_NM;
        this.ADRES = ADRES;
        this.TELNO = TELNO;
    }
}

