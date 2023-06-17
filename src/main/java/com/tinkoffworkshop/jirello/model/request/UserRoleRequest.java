package com.tinkoffworkshop.jirello.model.request;

import com.tinkoffworkshop.jirello.model.enums.RoleType;
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

    private RoleType role;
}
