package com.example.demo.hospital.controller;

import com.example.demo.hospital.dto.ResponseDto;
import com.example.demo.hospital.service.HospitalService;
import com.example.demo.response.ListResponseData;
import com.example.demo.response.SingleResponseData;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/hospital")
    public ListResponseData<ResponseDto> all() {
        return ListResponseData.of(hospitalService.findAllByOrderIdAsc());
    }

    @GetMapping("/hospital/save")
    public SingleResponseData<Long> save() throws ParseException, IOException {
        return SingleResponseData.of(hospitalService.save());
    }
}
