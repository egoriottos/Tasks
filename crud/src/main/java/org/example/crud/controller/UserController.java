package org.example.crud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.user.*;
import org.example.crud.configuration.View;
import org.example.crud.entity.User;
import org.example.crud.services.interfaces.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceInterface userService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {
        CreateUserCommand command = modelMapper.map(user, CreateUserCommand.class);
        UserResponse userResponse = modelMapper.map(userService.createUser(command), UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/all")
    @JsonView(View.Internal.class)
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers().stream().map(obj -> modelMapper.map(obj, UserResponse.class)).toList();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/info/{id}")
    @JsonView(View.Public.class)
    public ResponseEntity<UserResponseWithInfo> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseWithInfo.class));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        UpdateUserCommand command = modelMapper.map(user, UpdateUserCommand.class);
        userService.updateUser(id, command);
        return ResponseEntity.ok("User updated with id " + id + " email " + user.getEmail());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted with id " + id);
    }

    @GetMapping("/findBy/{email}")
    public ResponseEntity<UserResponseWithInfo> getUserByEmail(@PathVariable String email) {
        userService.findByEmail(email);
        return ResponseEntity.ok(modelMapper.map(userService.findByEmail(email), UserResponseWithInfo.class));
    }
}
