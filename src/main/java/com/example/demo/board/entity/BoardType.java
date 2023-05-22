package com.example.demo.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    community("커뮤니티"),
    post("건의사항");

    private String value;
}
