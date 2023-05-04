package com.example.demo.signup.repository;

import com.example.demo.signup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserid(String userid);

    Optional<User> findByUsername(String username);
}
