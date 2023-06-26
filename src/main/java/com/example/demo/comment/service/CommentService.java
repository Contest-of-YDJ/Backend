package com.example.demo.comment.service;

import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public Long save(Comment comment) { return commentRepository.save(comment).getId(); }


    @Transactional
    public Long update(Long id, String reply){
        commentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 댓글이 없습니다. id = "+id))
                .update(reply);

        return id;
    }

    @Transactional
    public void delete(Long id){
        Comment entity = commentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 댓글이 없습니다. id = "+id));

        commentRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 댓글이 없습니다. id = "+id));
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllDesc(Long id){
        return commentRepository.findAllByBoard_IdOrderByModifiedDateDesc(id);
    }
}
