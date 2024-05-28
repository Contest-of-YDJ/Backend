package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserid(String userid);

    Optional<Member> findByUsername(String username);

    Boolean existsByUserid(String userid);

    Boolean existsByEmail(String Email);
}

