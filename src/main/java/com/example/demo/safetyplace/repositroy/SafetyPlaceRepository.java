package com.example.demo.safetyplace.repositroy;

import com.example.demo.safetyplace.entity.SafetyPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafetyPlaceRepository extends JpaRepository<SafetyPlace, Long> {

}
