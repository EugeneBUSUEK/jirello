package com.tinkoffworkshop.jirello.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private Long id;

    private Long boardId;

    private String title;

    private String color;
}
