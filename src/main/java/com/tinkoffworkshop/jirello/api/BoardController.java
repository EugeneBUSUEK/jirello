package com.tinkoffworkshop.jirello.api;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest boardRequest) {

        BoardResponse response = boardService.createBoard(boardRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
