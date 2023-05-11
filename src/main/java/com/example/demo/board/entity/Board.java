package com.example.demo.board.entity;

import com.example.demo.jwt.BaseTimeEntity;
import com.example.demo.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500, nullable = false)
    private String title;
    @ManyToOne(fetch=FetchType.LAZY)
    private User username;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private LocalDateTime date;

    @Builder
    public Board(String title, User username, String content, LocalDateTime date){
        this.title = title;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
