package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.TaskRequest;
import com.tinkoffworkshop.jirello.model.response.TaskResponse;
import com.tinkoffworkshop.jirello.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<?> createTask(
            @RequestParam(name = "columnId") Long columnId,
            @RequestBody TaskRequest taskRequest
    ) {
        TaskResponse taskResponse = taskService.createTask(columnId, taskRequest);

        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getTasksByColumnId(@RequestParam(name = "columnId") Long columnId) {
        List<TaskResponse> taskResponseList = taskService.getTasksByColumnId(columnId);

        return new ResponseEntity<>(taskResponseList, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTasksById(
            @PathVariable(name = "taskId") Long taskId,
            @RequestBody TaskRequest taskRequest
    ) {
        TaskResponse taskResponse = taskService.updateTasksById(taskId, taskRequest);

        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTasksById(@PathVariable(name = "taskId") Long taskId) {
        TaskResponse taskResponse = taskService.deleteTasksById(taskId);

        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/{positionAfter}")
    public ResponseEntity<?> swapTaskPositionsInColumn(
            @PathVariable(name = "taskId") Long taskId,
            @PathVariable(name = "positionAfter") Integer positionAfter,
            @RequestParam(name = "columnId") Long columnId
//            @RequestParam(name = "positionBefore") Integer positionBefore
    ) {
        List<TaskResponse> taskResponseList = taskService.swapTaskPositionsInColumn(
                taskId,
                columnId,
//                positionBefore,
                positionAfter
        );

        return new ResponseEntity<>(taskResponseList, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/{positionAfter}/{columnId}")
    public ResponseEntity<?> swapTaskInColumns(
            @PathVariable(name = "taskId") Long taskId,
            @PathVariable(name = "positionAfter") Integer positionAfter,
            @PathVariable(name = "columnId") Long columnId,
//            @RequestParam(name = "boardId") Long boardId,
            @RequestParam(name = "newColumnId") Long newColumnId
//            @RequestParam(name = "positionBefore") Integer positionBefore

    ) {
        TaskResponse taskResponse = taskService.swapTaskInColumns(
                taskId,
                columnId,
                newColumnId,
//                boardId,
//                positionBefore,
                positionAfter
        );

        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }
}
