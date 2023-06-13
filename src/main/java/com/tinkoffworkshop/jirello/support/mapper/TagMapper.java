package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.request.TagOnBoardRequest;
import com.tinkoffworkshop.jirello.model.response.TagResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;

public class TagMapper {
    public static TagResponse mapToTagResponse(TagEntity tagEntity) {

        return TagResponse.builder()
                .id(tagEntity.getId())
                .boardId(tagEntity.getBoardEntity().getId())
                .title(tagEntity.getTitle())
                .color(tagEntity.getColor())
                .build();
    }

    public static TagEntity mapToTagEntity(TagOnBoardRequest tagOnBoardRequest, BoardEntity boardEntity) {

        return TagEntity.builder()
                .boardEntity(boardEntity)
                .title(tagOnBoardRequest.getTitle())
                .color(tagOnBoardRequest.getColor())
                .build();
    }
}
