package com.example.demo.board.repository;

import com.example.demo.board.entity.Board;
import com.example.demo.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDesc();

//    @Query("SELECT b FROM Board b LIMIT 0,5 ORDER BY b.date DESC")
//    List<Board> findTop5(BoardType boardType);
}
