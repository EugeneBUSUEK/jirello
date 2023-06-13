package com.tinkoffworkshop.jirello.support.mapper;

import com.tinkoffworkshop.jirello.model.dto.UserRoleDTO;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleMapper {
    public static List<UserRoleDTO> mapUserRolesToList(Map<UserEntity, RoleEntity> usersRoles) {
        List<UserRoleDTO> userRoleDTOList = new ArrayList<>();

        for (UserEntity user : usersRoles.keySet()) {
            userRoleDTOList.add(
                    UserRoleDTO.builder()
                            .user(UserResponseMapper.mapToUserResponse(user))
                            .role(usersRoles.get(user).getRole())
                            .build()
            );
        }

        return userRoleDTOList;
    }
}
