package com.tinkoffworkshop.jirello.support;

import com.tinkoffworkshop.jirello.model.response.UserResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;

public class UserResponseMapper {
    public static UserResponse mapToUserResponse(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getMail()
        );
    }
}
