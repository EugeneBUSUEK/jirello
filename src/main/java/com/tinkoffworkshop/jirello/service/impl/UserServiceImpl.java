package com.tinkoffworkshop.jirello.service.impl;

import com.tinkoffworkshop.jirello.model.request.UserRequest;
import com.tinkoffworkshop.jirello.model.response.UserResponse;
import com.tinkoffworkshop.jirello.persist.db.postgres.UserRepository;
import com.tinkoffworkshop.jirello.persist.db.postgres.entity.UserEntity;
import com.tinkoffworkshop.jirello.service.UserService;
import com.tinkoffworkshop.jirello.support.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder().
                name(userRequest.getName()).
                surname(userRequest.getSurname()).
                mail(userRequest.getMail()).
                build();
        UserEntity userEntityForResponse = userRepository.save(userEntity);
        UserResponse userResponse = UserResponseMapper.mapToUserResponse(userEntityForResponse);
        if (userResponse == null) {
            throw  new RuntimeException("nepoluchilos");
        }
        return userResponse;
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (!userEntity.isPresent()) {
            throw new RuntimeException("netu");
        }

        UserResponse userResponse = UserResponseMapper.mapToUserResponse(userEntity.get());
        return userResponse;
    }
}
