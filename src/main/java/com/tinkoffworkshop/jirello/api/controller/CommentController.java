package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.request.CommentRequest;
import com.tinkoffworkshop.jirello.model.response.CommentResponse;
import com.tinkoffworkshop.jirello.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<?> createCommentOnTask(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.createCommentOnTask(commentRequest);

        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getCommentsByTaskId(@RequestParam(name = "taskId") Long taskId) {
        List<CommentResponse> commentResponseList = commentService.getCommentsByTaskId(taskId);

        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable(name = "commentId") Long commentId) {
        CommentResponse commentResponse = commentService.deleteCommentById(commentId);

        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }
}
