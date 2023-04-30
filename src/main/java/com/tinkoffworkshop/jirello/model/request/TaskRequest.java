package com.tinkoffworkshop.jirello.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private String text;

    private String description;

    private Integer position;

    private List<Long> tagIds;

    private Long columnId;
}
