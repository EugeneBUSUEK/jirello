package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.CommentRequest;
import com.tinkoffworkshop.jirello.model.response.CommentResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.CommentRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.TaskRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.UserRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.CommentEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TaskEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import com.tinkoffworkshop.jirello.support.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public CommentResponse createCommentOnTask(CommentRequest commentRequest) {
        Optional<UserEntity> userEntity = userRepository.findById(commentRequest.getUserId());
        Optional<TaskEntity> taskEntity = taskRepository.findById(commentRequest.getTaskId());

        if (userEntity.isEmpty() || taskEntity.isEmpty()) {
            throw new RuntimeException("user or task not found");
        }

        CommentEntity commentEntity = CommentMapper.mapToCommentEntity(commentRequest, userEntity.get(), taskEntity.get());
        CommentEntity createdEntity = commentRepository.save(commentEntity);

        return CommentMapper.mapToCommentResponse(createdEntity);
    }

    public List<CommentResponse> getCommentsByTaskId(Long taskId) {
        List<CommentEntity> commentEntityList = commentRepository.findAllByTaskEntity_IdOrderByDateAsc(taskId);

        return commentEntityList.stream().map(CommentMapper::mapToCommentResponse).toList();
    }

    public CommentResponse deleteCommentById(Long commentId) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);

        if (commentEntity.isEmpty()) {
            throw new RuntimeException("comment not found");
        }

        CommentResponse commentResponse = CommentMapper.mapToCommentResponse(commentEntity.get());

        commentRepository.deleteById(commentId);

        return commentResponse;
    }
}
