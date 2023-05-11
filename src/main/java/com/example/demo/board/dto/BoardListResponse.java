package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;

import java.time.LocalDateTime;
public class BoardListResponse {
    private Long id;
    private String title;
    private User username;
    private String content;
    private LocalDateTime date;

    public BoardListResponse(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUsername();
        this.date = entity.getModifiedDate();
    }
}
