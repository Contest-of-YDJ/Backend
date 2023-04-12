package com.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class testApi {

    @GetMapping("/")
    public String test(){
        return "1";
    }
}
