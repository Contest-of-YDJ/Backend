package com.example.demo.hospital.entity;

import jakarta.persistence.*;
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
    private String factoryName;

    @Column(length = 50)
    private String businessManagePlace;

    @Column(length = 100)
    private String businessName;
}
