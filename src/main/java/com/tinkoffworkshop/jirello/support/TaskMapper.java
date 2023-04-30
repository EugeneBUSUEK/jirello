package com.tinkoffworkshop.jirello.support;

import com.tinkoffworkshop.jirello.model.request.TaskRequest;
import com.tinkoffworkshop.jirello.model.response.TaskResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;

import java.util.List;

public class TaskMapper {
    public static TaskEntity mapToTaskEntity(TaskRequest taskRequest, List<TagEntity> tagEntityList) {

        return TaskEntity.builder()
                .text(taskRequest.getText())
                .description(taskRequest.getDescription())
                .position(taskRequest.getPosition())
                .tags(tagEntityList)
                .columnEntity(ColumnEntity.builder().id(taskRequest.getColumnId()).build())
                .build();
    }

    public static TaskResponse mapToTaskResponse(TaskEntity createdTaskEntity) {

        return TaskResponse.builder()
                .id(createdTaskEntity.getId())
                .text(createdTaskEntity.getText())
                .description(createdTaskEntity.getDescription())
                .position(createdTaskEntity.getPosition())
                .tags(createdTaskEntity.getTags())
                .columnId(createdTaskEntity.getColumnEntity().getId())
                .build();
    }

    public static void updateEntityByTaskRequest(TaskEntity taskEntity, TaskRequest taskRequest) {
        taskEntity.setText(taskRequest.getText() != null ? taskRequest.getText() : taskEntity.getText());
        taskEntity.setDescription(taskRequest.getText() != null ? taskRequest.getText() : taskEntity.getText());
    }
}
