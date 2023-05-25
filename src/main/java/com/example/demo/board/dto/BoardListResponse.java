package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {
    private Long id;
    private String title;
    private User username;
    private LocalDateTime date;

    public BoardListResponse(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.username = entity.getUsername();
        this.date = entity.getModifiedDate();
    }
}
