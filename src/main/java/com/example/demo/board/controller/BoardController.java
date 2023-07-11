package com.example.demo.board.controller;

import com.example.demo.board.record.BoardListResponse;
import com.example.demo.board.record.BoardResponse;
import com.example.demo.board.record.BoardSaveRequest;
import com.example.demo.board.record.BoardUpdateRequest;
import com.example.demo.board.service.BoardService;
import com.example.demo.comment.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/")
    public ListResponseData<BoardListResponse> all() { return ListResponseData.of(boardService.findAllDesc()); }

    @PostMapping
    public SingleResponseData<Long> save(@RequestBody BoardSaveRequest request){
        return SingleResponseData.of(boardService.save(request.toEntity()));
    }

    @PatchMapping("/{id}")
    public SingleResponseData<Long> update(@PathVariable ("id") Long id, @RequestBody BoardUpdateRequest request){
        return SingleResponseData.of(boardService.update(id, request.title(), request.content(), request.timestamp()));
    }

    @DeleteMapping("/{id}")
    public SingleResponseData<Long> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return SingleResponseData.of(id);
    }

    @GetMapping("/{id}")
    public SingleResponseData<BoardResponse> findById(@PathVariable Long id){
        BoardResponse boardResponse = new BoardResponse(boardService.findById(id), commentService.findAllDesc(id));
        return SingleResponseData.of(boardResponse);
    }
}
