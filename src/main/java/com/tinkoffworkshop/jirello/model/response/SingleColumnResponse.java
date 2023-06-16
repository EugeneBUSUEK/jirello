package com.tinkoffworkshop.jirello.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleColumnResponse {

    private Long id;

    private String title;

    private Integer position;

    private List<TaskResponse> tasks;

    private Long boardId;
}
