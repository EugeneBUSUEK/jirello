package com.tinkoffworkshop.jirello.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnResponse {

    private Long id;

    private String title;

    private Integer position;

    private Long boardId;
}
