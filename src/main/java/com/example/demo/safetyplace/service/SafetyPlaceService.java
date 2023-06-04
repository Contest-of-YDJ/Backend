package com.example.demo.safetyplace.service;

import com.example.demo.safetyplace.entity.SafetyPlace;
import com.example.demo.safetyplace.repositroy.SafetyPlaceRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SafetyPlaceService {
    private final SafetyPlaceRepository safetyPlaceRepository;
    private final String apiUrl = "https://api.odcloud.kr/api/15002452/v1/uddi:4bc92095-bfa2-464f-8723-b65c6a336565";
    //인증키는 따로 보관해서 안전하게 사용해야할듯
    private final String serviceKey = "Infuser 9/gaTK+0+4IsgB43PG8bQtkz/UbkzJzw/N8PRL1ajzMOfsoa6l3zimjPL8d0ovIgoP9odKoqANhgzH0fgH5AfA==";
    private final String parameter = "?page=0&perPage=0";

    public String findAll() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization",serviceKey);

        RequestEntity<String> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(apiUrl + parameter));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        String response = responseEntity.getBody();
        parse(response);

        return response;
    }

    public void parse(String apiData) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object jsonObject = jsonParser.parse(apiData);
        JSONObject jsonMain = (JSONObject) jsonObject;
        JSONArray jsonArray = (JSONArray) jsonMain.get("data");

        for(int i=0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

            String factoryName = (String) jsonObject1.get("공사장명");
            String businessManagePlace = (String) jsonObject1.get("노동지청명");
            String businessName = (String) jsonObject1.get("사업장명");
            String permitDay = (String) jsonObject1.get("인정일");

            safetyPlaceRepository.save(new SafetyPlace(factoryName, businessManagePlace, businessName, permitDay));
        }
    }
}