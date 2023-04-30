package com.tinkoffworkshop.jirello.model.response;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;

    private String text;

    private String description;

    private Integer position;

    private List<TagEntity> tags;

    private Long columnId;
}
