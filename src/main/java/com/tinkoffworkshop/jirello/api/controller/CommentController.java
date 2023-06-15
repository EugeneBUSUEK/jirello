package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.CommentRequest;
import com.tinkoffworkshop.jirello.model.response.CommentResponse;
import com.tinkoffworkshop.jirello.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
@Tag(name = "comments", description = "comment resources")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "create comment")
    @PostMapping()
    public ResponseEntity<?> createCommentOnTask(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.createCommentOnTask(commentRequest);

        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "get comments by task id")
    @GetMapping()
    public ResponseEntity<?> getCommentsByTaskId(@RequestParam(name = "taskId") Long taskId) {
        List<CommentResponse> commentResponseList = commentService.getCommentsByTaskId(taskId);

        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
    }

    @Operation(summary = "delete comment by id")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable(name = "commentId") Long commentId) {
        CommentResponse commentResponse = commentService.deleteCommentById(commentId);

        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }
}
