package com.example.demo.user.record;

import com.example.demo.user.entity.User;
import lombok.Builder;

import org.springframework.security.crypto.password.PasswordEncoder;

public record JoinRecord(String userid, String email, String username, String password) {

    @Builder
    public JoinRecord {
        // Constructor body
    }

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .email(email)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .build();
    }
}