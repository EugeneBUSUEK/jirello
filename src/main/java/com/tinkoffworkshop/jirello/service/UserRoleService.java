package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.UserRoleRequest;
import com.tinkoffworkshop.jirello.persist.db.postgres.RoleRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.UserRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.RoleEntity;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Map<UserEntity, RoleEntity> getUserRoles(List<UserRoleRequest> users) {

        return users.stream().collect(Collectors.toMap(
                userRoleRequest -> {
                    var userEntity = userRepository.findById(userRoleRequest.getUserId());

                    if (userEntity.isEmpty()) throw new RuntimeException("User with id = " +
                            userRoleRequest.getUserId() + " not found");

                    return userEntity.get();
                },
                userRoleRequest -> {
                    var roleEntity = roleRepository.getRoleEntityByRole(userRoleRequest.getRole());

                    if (roleEntity.isEmpty()) throw new RuntimeException("Role " + userRoleRequest.getRole().getRole() + " not found");

                    return roleEntity.get();
                }
        ));
    }
}
