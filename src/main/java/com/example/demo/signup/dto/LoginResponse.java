package com.example.demo.signup.dto;

import com.example.demo.signup.entity.Role;
import com.example.demo.signup.entity.User;
import lombok.Getter;

@Getter
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String userid;
    private String picture;
    private Role role;
    private String password;
    private String jwtToken;

    public LoginResponse(User entity, String jwtToken){
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.userid = entity.getUserid();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
        this.password = entity.getPassword();
        this.jwtToken = jwtToken;
    }
}
