package com.example.demo.hospital.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class Response {

    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @Setter
    @XmlRootElement(name = "header")
    private static class Header{
        private String resultCode;
        private String resultMsg;
    }
    @Getter
    @Setter
    @XmlRootElement(name = "body")
    public static class Body{
        private Items items;
        private String numOfRows;
        private String pageNo;
        private String totalCount;

        @Getter
        @Setter
        @XmlRootElement(name = "items")
        public static class Items{
            private List<Item> item;

            @Getter
            @Setter
            @XmlRootElement(name="item")
            public static class Item{
                private String FCLTY_NM;
                private String ADRES;
                private String TELNO;
                private String X;
                private String Y;
            }
        }
    }
}

