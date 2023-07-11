package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.entity.Comment;
import com.example.demo.user.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponse{
    private Long id;
    private String title;
    private String content;
    private User username;
    private List<Comment> commentList;

    public BoardResponse(Board entity, List<Comment> commentList){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUsername();
        this.commentList = commentList;
    }
}
