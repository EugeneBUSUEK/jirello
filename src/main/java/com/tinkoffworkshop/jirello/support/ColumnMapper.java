package com.tinkoffworkshop.jirello.support;

import com.tinkoffworkshop.jirello.model.request.ColumnRequest;
import com.tinkoffworkshop.jirello.model.response.ColumnResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.BoardEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.ColumnEntity;

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
}
