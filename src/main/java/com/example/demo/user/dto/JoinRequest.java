package com.example.demo.user.dto;

import com.example.demo.user.entity.Role;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
public class JoinRequest {
    private String userid;
    private String email;
    private String username;
    private String password;

    @Builder
    public JoinRequest(String userid, String email, String username, String password){
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .username(username)
                .email(email)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();
    }
}
