package com.tinkoffworkshop.jirello.service;

import com.tinkoffworkshop.jirello.model.request.UserRequest;
import com.tinkoffworkshop.jirello.model.response.UserResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse deleteUsersById(Long id);
}
