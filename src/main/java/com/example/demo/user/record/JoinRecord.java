package com.example.demo.user.record;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.Builder;

import org.springframework.security.crypto.password.PasswordEncoder;

public record JoinRecord(String userid, String email, String username, String password) {

    @Builder
    public JoinRecord {
        // Constructor body
    }

    public User toEntity(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }
        if(userRepository.existsByUserid(userid)){
            throw new IllegalArgumentException("Userid already exists");
        }
        return User.builder()
                .username(username)
                .email(email)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .build();
    }
}