package com.tinkoffworkshop.jirello.model.response;

import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long id;

    private String title;

    private Map<UserEntity, RoleEntity> users;
}
