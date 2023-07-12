package com.example.demo.safetyplace.controller;

import com.example.demo.response.ListResponseData;
import com.example.demo.response.SingleResponseData;
import com.example.demo.safetyplace.record.SafetyPlaceResponseDto;
import com.example.demo.safetyplace.service.SafetyPlaceService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SafetyPlaceController {
    private final SafetyPlaceService safetyPlaceService;

    @GetMapping("/open-api")
    public ListResponseData<SafetyPlaceResponseDto> all(){
        return ListResponseData.of(safetyPlaceService.findAllByOrderByIdAsc());
    }

    @GetMapping("/open-api/save")
    public SingleResponseData<Long> save() throws UnsupportedEncodingException, ParseException {
        return SingleResponseData.of(safetyPlaceService.save());
    }

    @GetMapping("/open-api/search")
    public ListResponseData<SafetyPlaceResponseDto> search(@RequestParam("keyword")String keyword) {
        return ListResponseData.of(safetyPlaceService.search(keyword));
    }
}