package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.TagOnBoardRequest;
import com.tinkoffworkshop.jirello.model.response.TagResponse;
import com.tinkoffworkshop.jirello.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
@Tag(name = "tags", description = "tag resources")
public class TagController {
    private final TagService tagService;

    @Operation(summary = "get tags on board")
    @GetMapping()
    public ResponseEntity<?> getBoardTags(@RequestParam(name = "boardId") Long boardId) {
        List<TagResponse> tagResponseList = tagService.getBoardTags(boardId);

        return new ResponseEntity<>(tagResponseList, HttpStatus.OK);
    }

    @Operation(summary = "create tag on board")
    @PostMapping("/{boardId}")
    public ResponseEntity<?> createTagOnBoard(@RequestBody TagOnBoardRequest tagOnBoardRequest) {
        TagResponse tagResponse = tagService.createTagOnBoard(tagOnBoardRequest);

        return new ResponseEntity<>(tagResponse, HttpStatus.CREATED);
    }
}
