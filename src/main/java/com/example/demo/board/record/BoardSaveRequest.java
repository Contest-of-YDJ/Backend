package com.example.demo.board.record;

import com.example.demo.board.entity.Board;
import com.example.demo.member.entity.Member;

import java.util.Date;

public record BoardSaveRequest(String title, Member memberName, Date timestamp, String content) {
    public Board toEntity() {
        return new Board(title, memberName, content, timestamp);
    }
}
