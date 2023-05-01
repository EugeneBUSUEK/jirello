package com.tinkoffworkshop.jirello.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private String text;

    private Long date;

    private Long userId;

    private Long taskId;

    private String commentType;
}
