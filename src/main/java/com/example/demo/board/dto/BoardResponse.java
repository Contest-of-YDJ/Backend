package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Getter;

@Getter
public class BoardResponse {
    private Long id;
    private String title;

    private User username;
    private String content;

    public BoardResponse(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUsername();
    }
}
