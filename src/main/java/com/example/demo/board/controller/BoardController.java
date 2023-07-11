package com.example.demo.board.controller;

import com.example.demo.board.dto.BoardListResponse;
import com.example.demo.board.dto.BoardSaveRequest;
import com.example.demo.board.dto.BoardUpdateRequest;
import com.example.demo.board.service.BoardService;
import com.example.demo.response.ListResponseData;
import com.example.demo.response.SingleResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public ListResponseData<BoardListResponse> all() { return ListResponseData.of(boardService.findAllDesc()); }

    @PostMapping
    public SingleResponseData<Long> save(@RequestBody BoardSaveRequest request){
        return SingleResponseData.of(boardService.save(request.toEntity()));
    }

    @PatchMapping("/{id}")
    public SingleResponseData<Long> update(@PathVariable Long id, @RequestBody BoardUpdateRequest request){
        return SingleResponseData.of(boardService.update(id, request.getTitle(), request.getContent(), request.getTimestamp()));
    }


    @DeleteMapping("/{id}")
    public SingleResponseData<Long> delete(@PathVariable Long id){
        boardService.delete(id);
        return SingleResponseData.of(id);
    }
}
