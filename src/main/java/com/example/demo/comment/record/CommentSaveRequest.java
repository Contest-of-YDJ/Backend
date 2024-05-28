package com.example.demo.comment.record;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.User;

public record CommentSaveRequest(String reply, User user, Board board) {
    public Comment toEntity() {
        return new Comment(reply, user, board);
    }
}
