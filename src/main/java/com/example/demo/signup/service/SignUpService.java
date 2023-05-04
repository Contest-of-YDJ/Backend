package com.example.demo.signup.service;

import com.example.demo.signup.dto.JoinRequest;
import com.example.demo.signup.entity.User;


public interface SignUpService {
    Long join(JoinRequest joinRequest);
    User login(String userId, String password);
}
