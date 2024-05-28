package com.example.demo.jwt;

import com.example.demo.member.entity.MemberDetails;
import com.example.demo.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static com.example.demo.jwt.JwtProperties.*;
import static com.example.demo.jwt.JwtUtils.removePrefix;

//jwt 허가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository){
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override //토큰 정보로 유저의 역할 추출
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        String jwtHeader = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(jwtHeader) && jwtHeader.startsWith(TOKEN_PREFIX)){
            String jwtToken = removePrefix(jwtHeader);
            String username = JwtUtils.getClaim(jwtToken, "username");

            if (StringUtils.hasText(username)){
                MemberDetails user = new MemberDetails(memberRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("해당 아이디를 가진 유저가 없습니다.")));
                SecurityContextHolder.getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
            }
        }
        chain.doFilter(request, response);
    }

}
