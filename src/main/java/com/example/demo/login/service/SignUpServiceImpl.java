package com.example.demo.login.service;

import com.example.demo.login.dto.UserDto;
import com.example.demo.login.entity.User;
import com.example.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final UserRepository userRepository;

    @Override
    public Long join(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .build();

        return userRepository.save(user).getId();
    }
}
