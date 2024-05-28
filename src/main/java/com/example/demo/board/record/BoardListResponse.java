package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.member.entity.User;

import java.util.Date;

public record BoardListResponse(Long id, String title, User userName, Date timestamp) {
    public BoardListResponse(Board entity) {
        this(entity.getId(), entity.getTitle(), entity.getUserName(), entity.getTimestamp());
    }
}
