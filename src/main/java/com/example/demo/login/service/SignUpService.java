package com.example.demo.login.service;

import com.example.demo.login.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface SignUpService {
    Long join(UserDto userDto);
}
