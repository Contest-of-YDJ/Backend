package com.example.demo.config;

import com.example.demo.user.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()                               // cors 방지
                .csrf().disable()                           // csrf 방지
                //.formLogin().disable()                      // 기본 로그인 페이지 없애기
                //.headers().frameOptions().disable()
                // 권한 설정
                .authorizeRequests()
                .requestMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/profile").permitAll()
                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().logoutSuccessUrl("/");

        return http.build();
    }
}