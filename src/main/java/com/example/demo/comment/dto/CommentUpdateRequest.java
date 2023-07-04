package com.example.demo.comment.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Getter
@NoArgsConstructor
public class CommentUpdateRequest {
    private String reply;

    @Builder
    public CommentUpdateRequest(String reply) { this.reply = reply; }
}
