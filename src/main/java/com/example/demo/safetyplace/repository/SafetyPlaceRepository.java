package com.example.demo.safetyplace.repositroy;

import com.example.demo.safetyplace.entity.SafetyPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SafetyPlaceRepository extends JpaRepository<SafetyPlace, Long> {
    List<SafetyPlace> findBybusinessManagePlace(String keyword);

    @Query("SELECT sb FROM SafetyPlace sb ORDER BY sb.id ASC")
    List<SafetyPlace> findAllASC();
}
