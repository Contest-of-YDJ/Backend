package com.example.demo.signup.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id    // 내가 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 id 생성
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

   @Enumerated(EnumType.STRING)
   @Column
   private Role role;

   @Builder
   public User(String email, String username, String userid, String password, String picture, Role role){
       this.email = email;
       this.username = username;
       this.userid = userid;
       this.password = password;
       this.picture = picture;
       this.role = role;
   }

   public String getRoleKey() { return this.role.getKey(); }

}
