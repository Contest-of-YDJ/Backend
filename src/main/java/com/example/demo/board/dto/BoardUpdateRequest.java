package com.example.demo.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class BoardUpdateRequest {
    private String title;
    private String content;
    private Date timestamp;

    @Builder
    public BoardUpdateRequest(String title, String content, Date timestamp){
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }
}
