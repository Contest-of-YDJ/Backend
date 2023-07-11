package com.example.demo.user.repository;

import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserid(String userid);

    Optional<User> findByUsername(String username);

    Boolean existsByUserid(String userid);

    Boolean existsByEmail(String Email);
}

