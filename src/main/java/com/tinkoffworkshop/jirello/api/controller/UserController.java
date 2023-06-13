package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.UserRequest;
import com.tinkoffworkshop.jirello.model.response.UserResponse;
import com.tinkoffworkshop.jirello.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> userResponseList = userService.getAllUsers();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {
        UserResponse userResponse = userService.deleteUsersById(id);

        if (userResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
