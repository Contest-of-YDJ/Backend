package com.example.demo.comment.repository;

import com.example.demo.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard_IdOrderByModifiedDateDesc(Long id);
}
