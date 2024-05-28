package com.example.demo.member.controller;

import com.example.demo.jwt.JwtUtils;
import com.example.demo.response.SingleResponseData;
import com.example.demo.member.record.JoinRecord;
import com.example.demo.member.record.LoginRecord;
import com.example.demo.member.record.LoginResponseRecord;
import com.example.demo.member.entity.User;
import com.example.demo.member.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.jwt.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public SingleResponseData<Long> join(@RequestBody JoinRecord joinRecord) {
        return SingleResponseData.of(userService.join(joinRecord));
    }

    @PostMapping("/login")
    public SingleResponseData<LoginResponseRecord> login(HttpServletResponse response, @RequestBody LoginRecord loginRecord){
        LoginResponseRecord entity = userService.login(response, loginRecord.userid(), loginRecord.password());

        return SingleResponseData.of(entity);
    }
}