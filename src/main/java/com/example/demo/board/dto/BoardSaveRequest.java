package com.example.demo.board.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardSaveRequest {
    private String title;
    private User username;
    private String content;

    @Builder
    public BoardSaveRequest(String title, User username, String content){
        this.title = title;
        this.username = username;
        this.content = content;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .username(username)
                .content(content)
                .build();
    }
}
