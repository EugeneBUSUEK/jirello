package com.tinkoffworkshop.jirello.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String text;

    private Long date;

    private Long userId;

    private Long taskId;

    private String commentType;
}
