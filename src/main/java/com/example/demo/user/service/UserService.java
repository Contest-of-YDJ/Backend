package com.example.demo.user.service;

import com.example.demo.user.dto.JoinRequest;
import com.example.demo.user.entity.User;


public interface UserService {
    Long join(JoinRequest joinRequest);
    User login(String userId, String password);
}
