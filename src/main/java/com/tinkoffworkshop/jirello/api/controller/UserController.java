package com.tinkoffworkshop.jirello.api.controller;

import com.tinkoffworkshop.jirello.model.request.UserRequest;
import com.tinkoffworkshop.jirello.model.response.UserResponse;
import com.tinkoffworkshop.jirello.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "users", description = "user resources")
public class UserController {
    private final UserService userService;

    @Operation(summary = "create user")
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "get all users")
    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> userResponseList = userService.getAllUsers();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @Operation(summary = "delete user bu id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {
        UserResponse userResponse = userService.deleteUsersById(id);

        if (userResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
