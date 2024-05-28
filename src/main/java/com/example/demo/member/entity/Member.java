package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

   @Column
   private String picture;

   @Builder
   public Member(String email, String username, String userid, String password, String picture){
       this.email = email;
       this.username = username;
       this.userid = userid;
       this.password = password;
       this.picture = picture;
   }
}
