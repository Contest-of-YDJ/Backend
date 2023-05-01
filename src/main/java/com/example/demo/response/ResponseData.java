package com.example.demo.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ResponseData {
    private int statusCode;
    private String message;

    protected ResponseData(){
        this.statusCode = HttpStatus.OK.value();
        this.message = "success";
    }
}
