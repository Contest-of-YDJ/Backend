package com.example.demo.safetyplace.service;

import com.example.demo.safetyplace.entity.SafetyPlace;
import com.example.demo.safetyplace.repository.SafetyPlaceRepository;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SafetyPlaceService {
    private final SafetyPlaceRepository safetyPlaceRepository;
    private final String apiUrl = "https://api.odcloud.kr/api/15002452/v1/uddi:4bc92095-bfa2-464f-8723-b65c6a336565";
    @Value("${config.secret}")
    private String serviceKey;
    private final String parameter = "?page=0&perPage=0";

    @Transactional
    public String save() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", serviceKey);

        RequestEntity<String> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(apiUrl + parameter));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        String response = responseEntity.getBody();

        JSONParser jsonParser = new JSONParser();
        Object jsonObject = jsonParser.parse(response);
        JSONObject jsonMain = (JSONObject) jsonObject;
        JSONArray jsonArray = (JSONArray) jsonMain.get("data");

        for(int i=0; i < jsonArray.size(); i++){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

            String factoryName = String.valueOf(jsonObject1.get("공사장명"));
            String businessManagePlace = String.valueOf(jsonObject1.get("노동지청명"));
            String businessName = String.valueOf(jsonObject1.get("사업장명"));
            String permitDay = String.valueOf(jsonObject1.get("인정일"));

            SafetyPlace safetyPlace = SafetyPlace.builder()
                    .factoryName(factoryName)
                    .businessManagePlace(businessManagePlace)
                    .businessName(businessName)
                    .permitDay(permitDay)
                    .build();

            safetyPlaceRepository.save(safetyPlace);
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<SafetyPlace> search(String keyword){
        return safetyPlaceRepository.findBybusinessManagePlace(keyword);
    }

    @Transactional(readOnly = true)
    public List<SafetyPlace> findAllByOrderByIdAsc() {
        return safetyPlaceRepository.findAllByOrderByIdAsc();
    }
}