package com.example.demo.login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id    // 내가 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 id 생성
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String username;

    @Column
    private String userId;

    @Column(nullable = false)
    private String password;
}
