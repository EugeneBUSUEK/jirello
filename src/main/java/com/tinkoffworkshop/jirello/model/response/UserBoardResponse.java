package com.tinkoffworkshop.jirello.model.response;

import com.tinkoffworkshop.jirello.model.dto.UserRoleDTO;
import com.tinkoffworkshop.jirello.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBoardResponse {

    private Long id;

    private String title;

    private RoleType userRole;
}
