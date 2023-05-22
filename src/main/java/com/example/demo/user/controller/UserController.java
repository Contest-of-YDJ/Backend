package com.example.demo.user.controller;

import com.example.demo.jwt.JwtUtils;
import com.example.demo.response.SingleResponseData;
import com.example.demo.user.dto.JoinRequest;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.LoginResponse;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.jwt.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/members")
public class UserController {
    private final UserService signUpService;

    @PostMapping("/join")
    public SingleResponseData<Long> join(@RequestBody JoinRequest joinRequest){
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