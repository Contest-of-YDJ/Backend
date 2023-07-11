package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.user.entity.User;

import java.util.Date;

public record BoardSaveRequest(String title, User username, Date timestamp, String content) {
    public Board toEntity() {
        return new Board(title, username, content, timestamp);
    }
}
