package com.tinkoffworkshop.jirello.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagOnBoardRequest {

    private Long boardId;

    private String title;

    private String color;
}