package com.example.demo.safetyplace.controller;

import com.example.demo.safetyplace.service.SafetyPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SafetyPlaceController {
    private final SafetyPlaceService safetyPlaceService;

    @GetMapping("/open-api")
    public void call()throws UnsupportedEncodingException {
        safetyPlaceService.findAll();
    }
}
