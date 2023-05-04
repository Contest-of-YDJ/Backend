package com.example.demo.signup.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    private String userid;
    private String password;

    @Builder
    public LoginRequest(String userid, String password){
        this.userid = userid;
        this.password = password;
    }
}
