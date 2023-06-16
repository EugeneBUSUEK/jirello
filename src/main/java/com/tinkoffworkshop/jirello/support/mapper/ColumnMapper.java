package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.response.ColumnResponse;
import com.tinkoffworkshop.jirello.model.response.SingleColumnResponse;
import com.tinkoffworkshop.jirello.model.response.TaskResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;

import java.util.List;

public class ColumnMapper {
    public static ColumnEntity mapToColumnEntity(ColumnRequest columnRequest) {

        return ColumnEntity.builder()
                .title(columnRequest.getTitle())
                .position(columnRequest.getPosition())
                .boardEntity(
                        BoardEntity.builder()
                                .id(columnRequest.getBoardId())
                                .build()
                )
                .build();
    }

    public static ColumnResponse mapToColumnResponse(ColumnEntity columnEntity) {

        return ColumnResponse.builder()
                .id(columnEntity.getId())
                .title(columnEntity.getTitle())
                .position(columnEntity.getPosition())
                .boardId(columnEntity.getBoardEntity().getId())
                .build();
    }

    public static SingleColumnResponse mapToSingleColumnResponse(ColumnEntity columnEntity) {
        List<TaskEntity> taskEntityList = columnEntity.getTasks();
        List<TaskResponse> taskResponseList = taskEntityList.stream()
                .map(TaskMapper::mapToTaskResponse)
                .toList();
        return SingleColumnResponse.builder()
                .id(columnEntity.getId())
                .title(columnEntity.getTitle())
                .position(columnEntity.getPosition())
                .tasks(taskResponseList)
                .boardId(columnEntity.getBoardEntity().getId())
                .build();
    }
}
