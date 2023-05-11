package com.example.demo.user.service;

import com.example.demo.user.dto.JoinRequest;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    @Transactional
//    public Long join(UserDto userDto) {
//
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        User user = User.builder()
//                .email(userDto.getEmail())
//                .username(userDto.getUsername())
//                .userId(userDto.getUserId())
//                .password(userDto.getPassword())
//                .role(Role.USER)
//                .build();
//
//        return userRepository.save(user).getId();
//    }
    @Transactional
    public Long join(JoinRequest joinRequest){
        return userRepository.save(joinRequest.toEntity(passwordEncoder)).getId();
    }

    @Transactional(readOnly = true)
    public User login(String userid, String password){
        User entity = userRepository.findByUserid(userid).orElseThrow(()-> new IllegalArgumentException("Not Found Such User"));
        if(passwordEncoder.matches(password, entity.getPassword()))
            return entity;
        else return null;
    }



}
