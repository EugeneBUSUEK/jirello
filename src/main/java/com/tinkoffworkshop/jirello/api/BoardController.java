package com.tinkoffworkshop.jirello.api;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardByIdResponse;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest boardRequest) {
        BoardResponse response = boardService.createBoard(boardRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getBoardsByUserId(@RequestParam(name = "userId") Long userId) {
        List<BoardResponse> boardResponseList = boardService.getBoardsByUserId(userId);

        return new ResponseEntity<>(boardResponseList, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardById(@PathVariable Long boardId) {
        BoardByIdResponse boardByIdResponse = boardService.getBoardById(boardId);

        return new ResponseEntity<>(boardByIdResponse, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateBoardName(@RequestParam(name = "boardId") Long boardId, BoardRequest boardRequest) {
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

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoardById(
            @PathVariable(name = "boardId") Long boardId
    ) {
        boardService.deleteBoardById(boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
