package com.example.demo.safetyplace.service;

import com.example.demo.safetyplace.entity.SafetyPlace;
import com.example.demo.safetyplace.repositroy.SafetyPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;

@Component
public class SafetyPlaceService {
    private final String apiUrl = "https://api.odcloud.kr/api/15002452/v1/uddi:4bc92095-bfa2-464f-8723-b65c6a336565";
    private final String serviceKey = "Infuser 9/gaTK+0+4IsgB43PG8bQtkz/UbkzJzw/N8PRL1ajzMOfsoa6l3zimjPL8d0ovIgoP9odKoqANhgzH0fgH5AfA==";
    private final String parameter = "?page=0&perPage=0";

    public String findAll() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization",serviceKey);

        RequestEntity<String> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(apiUrl + parameter));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        String response = responseEntity.getBody();
        System.out.println(response);
        return response;
    }
}