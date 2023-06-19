package com.example.demo.hospital.service;

import com.example.demo.hospital.dto.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

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
        StringBuilder strBuilder = new StringBuilder("https://safemap.go.kr/openApiService/data/getInlcmdlcnstData.do"); /*URL*/
        strBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=K7ONP2Y7-K7ON-K7ON-K7ON-K7ONP2Y7KA"); /*Service Key*/
        strBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
        strBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/

        URL url = new URL(strBuilder.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-tpye", "application/json");

        System.out.println("Response Code:" + con.getResponseCode());
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

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

            int n = 0;
            for(Response.Body.Items.Item item : items.getItem()){
                System.out.println(n);
                System.out.println(item.getFCLTY_NM());
                System.out.println(item.getADRES());

                n++;
            }

        }catch (JAXBException e){
            e.printStackTrace();
        }

        return "1";
    }
}