package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardByIdResponse;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.model.response.UserBoardResponse;
import com.tinkoffworkshop.jirello.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
@Api(value = "board resources")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "create board")
    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest boardRequest) {
        BoardResponse response = boardService.createBoard(boardRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "get user's boards by user id")
    @GetMapping()
    public ResponseEntity<?> getBoardsByUserId(@RequestParam(name = "userId") Long userId) {
        List<UserBoardResponse> boardResponseList = boardService.getBoardsByUserId(userId);

        return new ResponseEntity<>(boardResponseList, HttpStatus.OK);
    }

    @ApiOperation(value = "get board by id")
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardById(@PathVariable(name = "boardId") Long boardId) {
        BoardByIdResponse boardByIdResponse = boardService.getBoardById(boardId);

        return new ResponseEntity<>(boardByIdResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "rename board")
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

    @ApiOperation(value = "delete board by id")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardById(
            @PathVariable(name = "boardId") Long boardId
    ) {
        boardService.deleteBoardById(boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
