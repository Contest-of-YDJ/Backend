package com.example.demo.hospital.repository;

import com.example.demo.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
