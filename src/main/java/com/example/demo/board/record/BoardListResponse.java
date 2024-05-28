package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.member.entity.Member;

import java.util.Date;

public record BoardListResponse(Long id, String title, Member memberName, Date timestamp) {
    public BoardListResponse(Board entity) {
        this(entity.getId(), entity.getTitle(), entity.getMemberaName(), entity.getTimestamp());
    }
}
