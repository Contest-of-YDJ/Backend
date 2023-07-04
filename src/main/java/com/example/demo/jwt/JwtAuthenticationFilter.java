package com.example.demo.jwt;

import com.example.demo.user.record.LoginRecord;
import com.example.demo.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.example.demo.jwt.JwtProperties.*;
import static com.example.demo.jwt.JwtUtils.createJwtToken1;

@RequiredArgsConstructor // jwt 인증
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager manager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        ObjectMapper mapper = new ObjectMapper();
        LoginRecord loginRecordDto = null;

        try{
            loginRecordDto = mapper.readValue(request.getInputStream(), LoginRecord.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        assert loginRecordDto != null;
        return manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRecordDto.userid(), loginRecordDto.password())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{
        response.addHeader(
                HEADER_STRING,
                TOKEN_PREFIX + createJwtToken1((User) authResult.getPrincipal())
        );
    }

}
