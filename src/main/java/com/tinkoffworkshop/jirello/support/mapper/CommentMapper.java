package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.request.CommentRequest;
import com.tinkoffworkshop.jirello.model.response.CommentResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.CommentEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;

public class CommentMapper {
    public static CommentEntity mapToCommentEntity(CommentRequest commentRequest, UserEntity userEntity, TaskEntity taskEntity) {

        return CommentEntity.builder()
                .text(commentRequest.getText())
                .date(commentRequest.getDate())
                .user(userEntity)
                .taskEntity(taskEntity)
                .type(commentRequest.getCommentType())
                .build();
    }

    public static CommentResponse mapToCommentResponse(CommentEntity commentEntity) {

        return CommentResponse.builder()
                .id(commentEntity.getId())
                .text(commentEntity.getText())
                .date(commentEntity.getDate())
                .userId(commentEntity.getUser().getId())
                .taskId(commentEntity.getTaskEntity().getId())
                .commentType(commentEntity.getType())
                .build();
    }
}
