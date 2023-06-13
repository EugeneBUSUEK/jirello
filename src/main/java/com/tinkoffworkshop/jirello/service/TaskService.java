package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.TaskRequest;
import com.tinkoffworkshop.jirello.model.response.TaskResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.TagRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.TaskRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import com.tinkoffworkshop.jirello.support.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    public TaskResponse createTask(Long columnId, TaskRequest taskRequest) {
        List<TagEntity> tagEntityList = tagRepository.findAllById(taskRequest.getTagIds());
        TaskEntity taskEntity = TaskMapper.mapToTaskEntity(taskRequest, tagEntityList);

        taskEntity.setPosition(1);

        List<TaskEntity> taskEntityList = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(taskEntity.getColumnEntity().getId());
        List<TaskEntity> taskEntityListForUpdate = changePositionsBetween(taskEntityList, taskEntityList.size() + 1, 1);

        taskRepository.saveAllAndFlush(taskEntityListForUpdate);

        TaskEntity createdTaskEntity = taskRepository.save(taskEntity);

        if (createdTaskEntity == null) {
            throw new RuntimeException("task not created");
        }

        return TaskMapper.mapToTaskResponse(createdTaskEntity);
    }

    public List<TaskResponse> getTasksByColumnId(Long columnId) {
        List<TaskEntity> taskEntityList = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(columnId);

        return taskEntityList.stream().map(TaskMapper::mapToTaskResponse).toList();
    }

    public TaskResponse updateTasksById(Long taskId, TaskRequest taskRequest) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskId);

        if (taskEntityOptional.isEmpty()) {
            throw new RuntimeException("task nit found");
        }

        TaskEntity taskForUpdate = taskEntityOptional.get();
        List<TagEntity> tagEntityList = tagRepository.findAllById(taskRequest.getTagIds());

        taskForUpdate.setId(taskId);
        taskForUpdate.setTags(tagEntityList);
        TaskMapper.updateEntityByTaskRequest(taskForUpdate, taskRequest);

        TaskEntity updatedTaskEntity = taskRepository.save(taskForUpdate);

        return TaskMapper.mapToTaskResponse(updatedTaskEntity);
    }

    @Transactional
    public TaskResponse deleteTasksById(Long taskId) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(taskId);

        if (taskEntity.isEmpty()) {
            throw new RuntimeException("task not found");
        }

        List<TaskEntity> taskEntityList = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(taskEntity.get().getColumnEntity().getId());
        List<TaskEntity> taskEntityListForUpdate = changePositionsBetween(taskEntityList, taskEntity.get().getPosition(), taskEntityList.size());

        taskRepository.saveAllAndFlush(taskEntityListForUpdate);

        TaskResponse taskResponse = TaskMapper.mapToTaskResponse(taskEntity.get());

        taskRepository.deleteById(taskId);

        return taskResponse;
    }

    public List<TaskResponse> swapTaskPositionsInColumn(
            Long taskId,
            Long columnId,
//            Integer positionBefore,
            Integer positionAfter) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskId);

        if (taskEntityOptional.isEmpty()) {
            throw new RuntimeException("task not found");
        }

        Integer positionBefore = taskEntityOptional.get().getPosition();
        List<TaskEntity> taskEntityList = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(columnId);
        List<TaskEntity> taskEntityListForUpdate = changePositionsBetween(taskEntityList, positionBefore, positionAfter);
        List<TaskEntity> updatedList = taskRepository.saveAllAndFlush(taskEntityListForUpdate);
        TaskEntity taskForUpdate = taskEntityOptional.get();

        taskForUpdate.setPosition(positionAfter);
        taskRepository.save(taskForUpdate);

        return updatedList.stream().map(TaskMapper::mapToTaskResponse).toList();
    }

    public TaskResponse swapTaskInColumns(
            Long taskId,
            Long columnId,
            Long newColumnId,
//            Long boardId,
//            Integer positionBefore,
            Integer positionAfter) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskId);

        if (taskEntityOptional.isEmpty()) {
            throw new RuntimeException("task not found");
        }

        Integer positionBefore = taskEntityOptional.get().getPosition();
        List<TaskEntity> taskEntityListOfCurrentColumn = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(columnId);
        List<TaskEntity> taskEntityListForUpdate = changePositionsBetween(taskEntityListOfCurrentColumn, positionBefore, taskEntityListOfCurrentColumn.size());

        taskRepository.saveAllAndFlush(taskEntityListForUpdate);

        List<TaskEntity> taskEntityListOfNewColumn = taskRepository.getTaskEntitiesByColumnEntity_IdOrderByPositionAsc(newColumnId);
        taskEntityListForUpdate = changePositionsBetween(taskEntityListOfNewColumn, taskEntityListOfNewColumn.size() + 1, positionAfter);

        taskRepository.saveAllAndFlush(taskEntityListForUpdate);

        TaskEntity taskForUpdate = taskEntityOptional.get();

        taskForUpdate.setPosition(positionAfter);
        taskForUpdate.setColumnEntity(
                ColumnEntity.builder().id(newColumnId).build()
        );

        TaskEntity updatedTaskEntity = taskRepository.save(taskForUpdate);

        return TaskMapper.mapToTaskResponse(updatedTaskEntity);
    }

    //---------------------------------------------------



    private List<TaskEntity> changePositionsBetween(List<TaskEntity> taskEntityList, Integer positionFrom, Integer positionTo) {
        List<TaskEntity> taskEntityListForUpdate;

        if (positionTo > positionFrom) {
            taskEntityListForUpdate = taskEntityList.stream()
                    .filter(taskEntity -> taskEntity.getPosition() > positionFrom && taskEntity.getPosition() <= positionTo)
                    .toList();
            changePositionsTo(taskEntityListForUpdate, -1);
        } else {
            taskEntityListForUpdate = taskEntityList.stream()
                    .filter(taskEntity -> taskEntity.getPosition() < positionFrom && taskEntity.getPosition() >= positionTo)
                    .toList();
            changePositionsTo(taskEntityListForUpdate, +1);
        }

        return taskEntityListForUpdate;
    }


    private void changePositionsTo(List<TaskEntity> taskEntityList, Integer dash) {
        taskEntityList.forEach(taskEntity -> taskEntity.setPosition(taskEntity.getPosition() + dash));
    }
}
