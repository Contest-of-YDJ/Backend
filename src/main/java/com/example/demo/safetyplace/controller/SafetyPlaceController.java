package com.example.demo.safetyplace.controller;

import com.example.demo.response.ListResponseData;
import com.example.demo.safetyplace.dto.SafetyPlaceResponseDto;
import com.example.demo.safetyplace.service.SafetyPlaceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
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
    public void call() throws UnsupportedEncodingException, ParseException {
        safetyPlaceService.findAll();
    }

    @GetMapping("/open-api/search")
    public ListResponseData<SafetyPlaceResponseDto> search(@RequestParam("keyword")String keyword) {
        return ListResponseData.of(safetyPlaceService.search(keyword));
    }

}
