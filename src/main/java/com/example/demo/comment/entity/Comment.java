package com.example.demo.comment.entity;

import com.example.demo.audit.AuditEntity;
import com.example.demo.board.entity.Board;
import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Comment(String reply, Member member, Board board){
        this.reply = reply;
        this.member = member;
        this.board = board;
    }

    public void update(String reply) { this.reply = reply; }
}
