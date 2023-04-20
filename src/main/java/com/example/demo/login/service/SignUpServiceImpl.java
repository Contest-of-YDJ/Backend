package com.example.demo.login.service;

import com.example.demo.login.dto.UserDto;
import com.example.demo.login.entity.User;
import com.example.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long join(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .build();

        return userRepository.save(user).getId();
    }
}
