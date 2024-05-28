package com.example.demo.board.entity;

import com.example.demo.audit.AuditEntity;
import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Board extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 500, nullable = false)
    private String title;
    @ManyToOne(fetch=FetchType.EAGER)
    private Member memberaName;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public Board(String title, Member memberName, String content, Date timestamp){
        this.title = title;
        this.memberaName = memberName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public void update(String title, String content, Date timestamp){
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }
}
