package com.example.demo.board.service;

import com.example.demo.board.dto.BoardListResponse;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board){
        return boardRepository.save(board).getId();
    }

    @Transactional
    public Long update(Long id, String title, String content, Date timestamp){
        boardRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 게시글이 없습니다. id = "+id))
                .update(title, content, timestamp);
        return id;
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id = "+id));
        boardRepository.delete(board);
    }

    @Transactional(readOnly=true)
    public Board findById(long id){
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return entity;
    }

    @Transactional(readOnly = true)
    public List<BoardListResponse> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponse::new)
                .collect(Collectors.toList());
    }
}
