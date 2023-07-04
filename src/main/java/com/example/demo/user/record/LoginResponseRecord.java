package com.example.demo.user.record;

import com.example.demo.user.entity.User;

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
