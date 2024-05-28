package com.example.demo.jwt;

import com.example.demo.member.record.LoginRecord;
import com.example.demo.member.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.demo.jwt.JwtProperties.*;
import static com.example.demo.jwt.JwtUtils.createJwtToken1;

@RequiredArgsConstructor // jwt 인증
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager manager;

    @Override //인증 시도
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        ObjectMapper mapper = new ObjectMapper();
        LoginRecord loginRecord = null;

        try{
            loginRecord = mapper.readValue(request.getInputStream(), LoginRecord.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        assert loginRecord != null;
        return manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRecord.userid(), loginRecord.password())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){
        response.addHeader(
                HEADER_STRING,
                TOKEN_PREFIX + createJwtToken1((User) authResult.getPrincipal())
        );
    }

}
