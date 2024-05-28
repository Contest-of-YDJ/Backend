package com.example.demo.member.record;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.Builder;

import org.springframework.security.crypto.password.PasswordEncoder;

public record JoinRecord(String userid, String email, String username, String password) {

    @Builder
    public JoinRecord {
        // Constructor body
    }

    public Member toEntity(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        if (memberRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }
        if(memberRepository.existsByUserid(userid)){
            throw new IllegalArgumentException("Userid already exists");
        }
        return Member.builder()
                .username(username)
                .email(email)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .build();
    }
}