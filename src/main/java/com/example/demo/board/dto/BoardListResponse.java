package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class BoardListResponse {
    private Long id;
    private String title;
    private User username;
    private Date timestamp;

    public BoardListResponse(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.username = entity.getUsername();
        this.timestamp = entity.getTimestamp();
    }
}
