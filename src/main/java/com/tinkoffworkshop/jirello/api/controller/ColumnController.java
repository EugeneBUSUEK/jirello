package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.response.ColumnResponse;
import com.tinkoffworkshop.jirello.service.ColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("columns")
@RequiredArgsConstructor
@Tag(name = "columns", description = "column resources")
public class ColumnController {
    private final ColumnService columnService;

    @Operation(summary = "create column")
    @PostMapping()
    public ResponseEntity<?> createColumnOnBoard(@RequestBody ColumnRequest columnRequest) {
        ColumnResponse columnResponse = columnService.createColumnOnBoard(columnRequest);

        return new ResponseEntity<>(columnResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "get columns on board by bord id")
    @GetMapping()
    public ResponseEntity<?> getColumnsOnBoard(@RequestParam Long boardId) {
        List<ColumnResponse> columnResponseList = columnService.getColumnsOnBoard(boardId);

        return new ResponseEntity<>(columnResponseList, HttpStatus.OK);
    }

    @Operation(summary = "delete column from board")
    @DeleteMapping("/{columnId}")
    public ResponseEntity<?> deleteColumnFromBoard(
            @PathVariable(name = "columnId") Long columnId,
            @RequestParam(name = "boardId") Long boardId
    ) {
        List<ColumnResponse> columnResponseList = columnService.deleteColumnFromBoard(columnId, boardId);

        return new ResponseEntity<>(columnResponseList, HttpStatus.OK);
    }

    @Operation(summary = "update column")
    @PutMapping("/{columnId}")
    public ResponseEntity<?> updateColumnOnBoard(
            @PathVariable(name = "columnId") Long columnId,
            @RequestParam(name = "boardId") Long boardId,
            @RequestBody ColumnRequest columnRequest
    ) {
        ColumnResponse columnResponse = columnService.updateColumnOnBoard(columnId, boardId, columnRequest);

        return new ResponseEntity<>(columnResponse, HttpStatus.OK);
    }

    @Operation(summary = "change column position on board")
    @PutMapping("/{columnId}/{positionAfter}")
    public ResponseEntity<?> swapColumnPositions(
            @PathVariable(name = "columnId") Long columnId,
            @PathVariable(name = "positionAfter") Integer positionAfter,
            @RequestParam(name = "boardId") Long boardId,
            @RequestParam(name = "positionBefore") Integer positionBefore
    ) {
        ColumnResponse columnResponse = columnService.swapColumnPositions(
                columnId,
                boardId,
//                positionBefore,
                positionAfter
        );

        return new ResponseEntity<>(columnResponse, HttpStatus.OK);
    }
}
