package com.tinkoffworkshop.jirello.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {

    private Long userId;

    private Long roleId;
}
