package com.example.demo.member.service;

import com.example.demo.member.record.JoinRecord;
import com.example.demo.member.entity.User;
import com.example.demo.member.record.LoginResponseRecord;
import jakarta.servlet.http.HttpServletResponse;


public interface UserService {
    Long join(JoinRecord joinRecord);
    LoginResponseRecord login(HttpServletResponse response, String userId, String password);
}
