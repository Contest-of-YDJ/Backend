package com.example.demo.hospital.service;

import com.example.demo.hospital.dto.Response;
import com.example.demo.hospital.record.ResponseRecord;
import com.example.demo.hospital.entity.Hospital;
import com.example.demo.hospital.repository.HospitalRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    @Value("${config.hospitalsecret}")
    private String serviceKey;

    @Transactional
    public String save() throws ParseException, IOException {
        StringBuilder strBuilder = new StringBuilder("https://safemap.go.kr/openApiService/data/getInlcmdlcnstData.do");
        strBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey);
        strBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        strBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("500", "UTF-8"));

        URL url = new URL(strBuilder.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-tpye", "application/json");

        BufferedReader br;

        if(con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null){
            sb.append(line);
        }

        br.close();
        con.disconnect();

        String xml = sb.toString();

        Map<String, Response> result = new HashMap<>();
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Response apiResponse = (Response)unmarshaller.unmarshal(new StringReader(xml));
            result.put("response",apiResponse);
            Response.Body.Items items = apiResponse.getBody().getItems();

            for(Response.Body.Items.Item item : items.getItem()){
                Hospital hospital = Hospital.builder()
                        .FCLTY_NM(item.getFCLTY_NM())
                        .ADRES(item.getADRES())
                        .TELNO(item.getTELNO())
                        .build();

                hospitalRepository.save(hospital);
            }

        }catch (JAXBException e){
            e.printStackTrace();
        }

        return "success";
    }

    @Transactional(readOnly = true)
    public List<ResponseRecord> findAllByOrderIdAsc() {
        return hospitalRepository.findAllByOrderByIdAsc().stream()
                .map(ResponseRecord::fromEntity)
                .collect(Collectors.toList());
    }
}