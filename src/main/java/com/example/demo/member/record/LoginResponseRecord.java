package com.example.demo.member.record;

import com.example.demo.member.entity.User;

public record LoginResponseRecord(
        Long id,
        String username,
        String email,
        String userid,
        String picture,
        String password,
        String jwtToken
) {
    public LoginResponseRecord(User entity, String jwtToken) {
        this(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getUserid(),
                entity.getPicture(), entity.getPassword(), jwtToken);
    }
}