package com.example.demo.comment.controller;

import com.example.demo.comment.dto.CommentSaveRequest;
import com.example.demo.comment.dto.CommentUpdateRequest;
import com.example.demo.comment.service.CommentService;
import com.example.demo.response.SingleResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public SingleResponseData<Long> save(@RequestBody CommentSaveRequest request){
        return SingleResponseData.of(commentService.save(request.toEntity()));
    }

    @PutMapping("/{id}")
    public SingleResponseData<Long> update(@PathVariable Long id, @RequestBody CommentUpdateRequest request){
        return SingleResponseData.of(commentService.update(id, request.getReply()));
    }

    @DeleteMapping("/{id}")
    public SingleResponseData<Long> delete(@PathVariable Long id){
        commentService.delete(id);
        return SingleResponseData.of(id);
    }
}
