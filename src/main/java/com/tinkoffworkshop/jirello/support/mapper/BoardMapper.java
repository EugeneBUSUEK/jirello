package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.request.BoardRequest;
import com.tinkoffworkshop.jirello.model.response.BoardByIdResponse;
import com.tinkoffworkshop.jirello.model.response.BoardResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;

import java.util.Map;

public class BoardMapper {
    public static BoardEntity mapToBoardEntity(BoardRequest boardRequest, Map<UserEntity, RoleEntity> users) {

        return BoardEntity.builder()
                .title(boardRequest.getTitle())
                .usersRoles(users)
                .build();
    }

    public static BoardResponse mapToBoardResponse(BoardEntity boardEntity) {

        return BoardResponse.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .users(boardEntity.getUsersRoles())
                .build();
    }

    public static BoardByIdResponse mapToBoardByIdResponse(BoardEntity boardEntity) {

        return BoardByIdResponse.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .users(RoleMapper.mapUserRolesToList(boardEntity.getUsersRoles()))
                .columnIds(boardEntity.getColumns().stream()
                        .map(columnEntity -> columnEntity.getId())
                        .toList())
                .build();
    }
}
