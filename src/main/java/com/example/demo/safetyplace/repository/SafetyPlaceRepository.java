package com.example.demo.safetyplace.repository;

import com.example.demo.safetyplace.entity.SafetyPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SafetyPlaceRepository extends JpaRepository<SafetyPlace, Long> {
    List<SafetyPlace> findBybusinessManagePlace(String keyword);
    List<SafetyPlace> findAllByOrderByIdAsc();
}
