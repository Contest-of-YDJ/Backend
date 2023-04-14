package com.example.demo.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class testApi {

    @GetMapping("/")
    public String test(){
        return "Connected Success";
    }
}
