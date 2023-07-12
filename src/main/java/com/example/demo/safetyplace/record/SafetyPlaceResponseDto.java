package com.example.demo.safetyplace.record;

import com.example.demo.safetyplace.entity.SafetyPlace;

public record SafetyPlaceResponseDto(String factoryName, String businessManagePlace, String businessName, String permitDay) {
    public SafetyPlaceResponseDto(SafetyPlace safetyPlace){
        this(safetyPlace.getFactoryName(), safetyPlace.getBusinessManagePlace(), safetyPlace.getBusinessName(), safetyPlace.getPermitDay());
    }

    public SafetyPlace toEntity(){
        return new SafetyPlace(factoryName, businessManagePlace, businessName, permitDay);
    }
}