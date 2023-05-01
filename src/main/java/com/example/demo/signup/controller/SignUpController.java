package com.example.demo.signup.controller;

import com.example.demo.jwt.JwtUtils;
import com.example.demo.response.SingleResponseData;
import com.example.demo.signup.dto.JoinRequest;
import com.example.demo.signup.dto.LoginRequest;
import com.example.demo.signup.dto.LoginResponse;
import com.example.demo.signup.dto.UserDto;
import com.example.demo.signup.entity.User;
import com.example.demo.signup.service.SignUpService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.example.demo.jwt.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/members")
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/join") // join으로 변경 건의
    public SingleResponseData<Long> join(@RequestBody JoinRequest joinRequest){
//        if(signUpService.join(userdto) > 0){
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return SingleResponseData.of(signUpService.join(joinRequest));
    }

    @PostMapping("/login")
    public SingleResponseData<LoginResponse> login(HttpServletResponse response, @RequestBody LoginRequest loginRequest){
        User entity = signUpService.login(loginRequest.getUserid(), loginRequest.getPassword());
        if (entity == null){
            throw new IllegalArgumentException("Incorrect Password");
        }

        String jwtToken = TOKEN_PREFIX.concat(JwtUtils.createJwtToken1(entity));
        response.addHeader(HEADER_STRING,jwtToken);

        LoginResponse loginResponse = new LoginResponse(entity, jwtToken);
        return SingleResponseData.of(loginResponse);
    }
}