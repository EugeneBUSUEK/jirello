package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardByIdResponse;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.model.response.UserBoardResponse;
import com.tinkoffworkshop.jirello.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
@Tag(name = "boards", description = "board resources")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "create board")
    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest boardRequest) {
        BoardResponse response = boardService.createBoard(boardRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "get user's boards by user id")
    @GetMapping()
    public ResponseEntity<?> getBoardsByUserId(@RequestParam(name = "userId") Long userId) {
        List<UserBoardResponse> boardResponseList = boardService.getBoardsByUserId(userId);

        return new ResponseEntity<>(boardResponseList, HttpStatus.OK);
    }

    @Operation(summary = "get board by id")
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardById(@PathVariable(name = "boardId") Long boardId) {
        BoardByIdResponse boardByIdResponse = boardService.getBoardById(boardId);

        return new ResponseEntity<>(boardByIdResponse, HttpStatus.OK);
    }

    @Operation(summary = "rename board")
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoardName(@PathVariable(name = "boardId") Long boardId, BoardRequest boardRequest) {
        BoardResponse boardResponse = boardService.updateBoardName(boardId, boardRequest);

        return new ResponseEntity<>(boardResponse, HttpStatus.OK);
    }

//    @PutMapping("/{boardId}")
//    public ResponseEntity<?> updateBoardParticipants(
//            @PathVariable(name = "boardId") Long boardId,
//            @RequestParam(name = "userId") Long userId
//    ) {
//        BoardResponse boardResponse = boardService.updateBoardParticipants(boardId, userId);
//
//        return new ResponseEntity<>(boardResponse, HttpStatus.OK);
//    }

    @Operation(summary = "delete board by id")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardById(
            @PathVariable(name = "boardId") Long boardId
    ) {
        boardService.deleteBoardById(boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
