package com.example.demo.comment.dto;

import com.example.demo.board.entity.Board;
import com.example.demo.comment.entity.Comment;
import com.example.demo.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveRequest {
    private String reply;
    private User user;
    private Board board;

//    @Builder
//    public CommentSaveRequest(String reply, User user, Board board){
//        this.reply = reply;
//        this.user = user;
//        this.board = board;
//    }

//    public Comment toEntity(){
//        return Comment.builder()
//                .reply(reply)
//                .user(user)
//                .board(board)
//                .build();
//    }

    public Comment toEntity(){
        return new Comment(reply, user, board);
    }
}
