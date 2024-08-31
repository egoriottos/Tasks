package org.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt.UserAuthRequest;
import org.example.jwt.UserRequest;
import org.example.jwt.entity.User;
import org.example.jwt.tetsService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserRequest user) {
       return ResponseEntity.ok(userService.createUser(modelMapper.map(user, User.class)));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody UserAuthRequest user) {
        return ResponseEntity.ok(userService.auth(modelMapper.map(user, User.class)));
    }

    @GetMapping("/secure")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello world");
    }
}
