package com.example.demo.login.controller;

import com.example.demo.login.dto.UserDto;
import com.example.demo.login.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/new")
    public ResponseEntity signUp(@RequestBody UserDto userdto){
        return ResponseEntity.ok(signUpService.join(userdto));
    }
}
