package com.example.demo.safetyplace.dto;

import com.example.demo.safetyplace.entity.SafetyPlace;

public class SafetyPlaceResponseDto {
    private Long id;
    private String factoryName;
    private String businessManagePlace;
    private String businessName;
    private String permitDay;

    public SafetyPlaceResponseDto(SafetyPlace safetyPlace){
        this.factoryName = safetyPlace.getFactoryName();
        this.businessManagePlace = safetyPlace.getBusinessManagePlace();
        this.businessName = safetyPlace.getBusinessName();
        this.permitDay = safetyPlace.getPermitDay();
    }

    public SafetyPlace toEntity(){
        return SafetyPlace.builder()
                .factoryName(factoryName)
                .businessManagePlace(businessManagePlace)
                .businessName(businessName)
                .permitDay(permitDay)
                .build();
    }
}
