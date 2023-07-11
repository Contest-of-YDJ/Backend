package com.example.demo.board.record;

import java.util.Date;

public record BoardUpdateRequest(String title, String content, Date timestamp) {
    public BoardUpdateRequest {

    }
}
