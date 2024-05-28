package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.User;

import java.util.List;

public record BoardResponse(Long id, String title, String content, User userName, List<Comment> commentList){
    public BoardResponse(Board entity, List<Comment> commentList){
        this(entity.getId(), entity.getTitle(), entity.getContent(), entity.getUserName(),commentList);
    }
}