package com.example.demo.hospital.service;

import com.example.demo.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

@Service
@RequiredArgsConstructor
public class HospitalService {

    /*
    private final HospitalRepository hospitalRepository;
    private final String apiUrl = "http://safemap.go.kr/openApiService/data/getInlcmdlcnstData.do?";
    @Value("${config.hospitalsecret}")
    private String servicekey;
    private final String parameter = servicekey+"&pageNo=1&numOfRows=10&type=JSON";
    */


    @Transactional
    public String save() throws ParseException, IOException {
        StringBuilder strBuilder = new StringBuilder("http://safemap.go.kr/openApiService/data/getInlcmdlcnstData.do"); /*URL*/
        strBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=K7ONP2Y7-K7ON-K7ON-K7ON-K7ONP2Y7KA"); /*Service Key*/
        strBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
        strBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        strBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*xml(기본값), JSON*/
        URL url = new URL(strBuilder.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        System.out.println(con.toString());


        return "1";
    }
}
