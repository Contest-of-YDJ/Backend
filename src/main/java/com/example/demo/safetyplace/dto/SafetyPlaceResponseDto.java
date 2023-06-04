package com.example.demo.safetyplace.dto;

import com.example.demo.safetyplace.entity.SafetyPlace;

public class SafetyPlaceResponseDto {
    private Long id;
    private String factoryName;
    private int businessCount;
    private String businessManagePlace;
    private String businessName;
    private String permitDay;

    public SafetyPlaceResponseDto(String factoryName, int businessCount, String businessManagePlace, String businessName, String permitDay) {
        this.factoryName = factoryName;
        this.businessCount = businessCount;
        this.businessManagePlace = businessManagePlace;
        this.businessName = businessName;
        this.permitDay = permitDay;
    }

    public SafetyPlace toEntity(){
        return SafetyPlace.builder()
                .factoryName(factoryName)
                .businessCount(businessCount)
                .businessManagePlace(businessManagePlace)
                .businessName(businessName)
                .permitDay(permitDay)
                .build();
    }
}
