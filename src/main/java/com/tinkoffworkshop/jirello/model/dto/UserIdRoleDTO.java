package com.tinkoffworkshop.jirello.model.dto;

import com.tinkoffworkshop.jirello.model.enums.RoleType;
import com.tinkoffworkshop.jirello.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIdRoleDTO {
    UserResponse user;

    RoleType role;
}
