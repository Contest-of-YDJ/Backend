package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BoardSaveRequest {
    private String title;
    private User username;
    private Date timestamp;
    private String content;

    @Builder
    public BoardSaveRequest(String title, User username, String content, Date timestamp){
        this.title = title;
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .username(username)
                .content(content)
                .timestamp(timestamp)
                .build();
    }
}
