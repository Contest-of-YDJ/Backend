package com.example.demo.user.service;

import com.example.demo.user.record.JoinRecord;
import com.example.demo.user.entity.User;


public interface UserService {
    Long join(JoinRecord joinRecord);
    User login(String userId, String password);
}
