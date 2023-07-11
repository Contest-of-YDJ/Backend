package com.example.demo.user.controller;

import com.example.demo.jwt.JwtUtils;
import com.example.demo.response.SingleResponseData;
import com.example.demo.user.record.JoinRecord;
import com.example.demo.user.record.LoginRecord;
import com.example.demo.user.record.LoginResponseRecord;
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
    public SingleResponseData<Long> join(@RequestBody JoinRecord joinRecord) {
        return SingleResponseData.of(signUpService.join(joinRecord));
    }

    @PostMapping("/login")
    public SingleResponseData<LoginResponseRecord> login(HttpServletResponse response, @RequestBody LoginRecord loginRecord){
        User entity = signUpService.login(loginRecord.userid(), loginRecord.password());
        if (entity == null){
            throw new IllegalArgumentException("Incorrect Password");
        }

        String jwtToken = TOKEN_PREFIX.concat(JwtUtils.createJwtToken1(entity));
        response.addHeader(HEADER_STRING,jwtToken);

        LoginResponseRecord loginResponseRecord = new LoginResponseRecord(entity, jwtToken);
        return SingleResponseData.of(loginResponseRecord);
    }
}