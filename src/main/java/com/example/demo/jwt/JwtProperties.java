package com.example.demo.jwt;

public interface JwtProperties {
    String SECRET = "key";
    int EXPIRATION_TIME = 3600000; //1시간
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
