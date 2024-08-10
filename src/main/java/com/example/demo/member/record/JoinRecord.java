package com.example.demo.member.record;

import com.example.demo.exception.ExceptionCode;
import com.example.demo.member.entity.User;
import com.example.demo.member.repository.UserRepository;
import com.example.demo.member.service.BusinessLogicException;
import lombok.Builder;

import org.springframework.security.crypto.password.PasswordEncoder;

public record JoinRecord(String userid, String email, String username, String password) {

    @Builder
    public JoinRecord {
        // Constructor body
    }

    public User toEntity(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        if (userRepository.existsByEmail(email)){
            System.out.println("이메일 충돌");
            throw new BusinessLogicException(ExceptionCode.MEMBER_CONFLICT);
        }
        if(userRepository.existsByUserid(userid)){
            System.out.println("유저 id 충돌");
            throw new BusinessLogicException(ExceptionCode.MEMBER_CONFLICT);
        }
        return User.builder()
                .username(username)
                .email(email)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .build();
    }
}