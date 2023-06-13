package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.response.ColumnResponse;
import com.tinkoffworkshop.jirello.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("columns")
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    @PostMapping()
    public ResponseEntity<?> createColumnOnBoard(@RequestBody ColumnRequest columnRequest) {
        ColumnResponse columnResponse = columnService.createColumnOnBoard(columnRequest);

        return new ResponseEntity<>(columnResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getColumnsOnBoard(@RequestParam Long boardId) {
        List<ColumnResponse> columnResponseList = columnService.getColumnsOnBoard(boardId);

        return new ResponseEntity<>(columnResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteColumnFromBoard(
            @PathVariable(name = "columnId") Long columnId,
            @RequestParam(name = "boardId") Long boardId
    ) {
        List<ColumnResponse> columnResponseList = columnService.deleteColumnFromBoard(columnId, boardId);

        return new ResponseEntity<>(columnResponseList, HttpStatus.OK);
    }

    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateColumnOnBoard(
            @PathVariable(name = "columnId") Long columnId,
            @RequestParam(name = "boardId") Long boardId,
            @RequestBody ColumnRequest columnRequest
    ) {
        ColumnResponse columnResponse = columnService.updateColumnOnBoard(columnId, boardId, columnRequest);

        return new ResponseEntity<>(columnResponse, HttpStatus.OK);
    }

    @PutMapping("/{columnId}/{boardId}")
    public ResponseEntity<?> swapColumnPositions(
            @PathVariable(name = "columnId") Long columnId,
            @PathVariable(name = "boardId") Long boardId,
            @RequestParam(name = "positionBefore") Integer positionBefore,
            @RequestParam(name = "positionAfter") Integer positionAfter
    ) {
        ColumnResponse columnResponse = columnService.swapColumnPositions(
                columnId,
                boardId,
                positionBefore,
                positionAfter
        );

        return new ResponseEntity<>(columnResponse, HttpStatus.OK);
    }
}
