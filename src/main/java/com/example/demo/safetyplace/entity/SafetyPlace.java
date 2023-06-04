package com.example.demo.safetyplace.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class SafetyPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String factoryName;

    @Column(length = 50)
    private String businessManagePlace;

    @Column(length = 100)
    private String businessName;

    @Column
    private String permitDay;

    @Builder
    public SafetyPlace(String factoryName, String businessManagePlace, String businessName, String permitDay){
        this.factoryName = factoryName;
        this.businessManagePlace = businessManagePlace;
        this.businessName = businessName;
        this.permitDay = permitDay;
    }
}
