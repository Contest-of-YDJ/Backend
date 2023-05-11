package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

public class BoardSaveRequest {
    private String title;
    private User username;
    private String content;
    private LocalDateTime date;

    @Builder
    public BoardSaveRequest(String title, User username, String content, LocalDateTime date){
        this.title = title;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .username(username)
                .content(content)
                .date(date)
                .build();
    }
}
