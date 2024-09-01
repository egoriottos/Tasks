package org.example.jwtwithaccessrights.controllers;

import lombok.RequiredArgsConstructor;
import org.example.jwtwithaccessrights.dto.AuthRequest;
import org.example.jwtwithaccessrights.dto.CreateRequest;
import org.example.jwtwithaccessrights.entity.User;
import org.example.jwtwithaccessrights.repository.UserRepository;
import org.example.jwtwithaccessrights.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<String> create(CreateRequest createRequest) {
        return ResponseEntity.ok(userService.createUser(modelMapper.map(createRequest, User.class)));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(AuthRequest authRequest) {
        return ResponseEntity.ok(userService.login(modelMapper.map(authRequest, User.class)));
    }

    @GetMapping("/moderator")
    public ResponseEntity<String> moderator() {
        return ResponseEntity.ok("Hello moderator");
    }

    @GetMapping("/super_admin")
    public ResponseEntity<String> superAdmin() {
        return ResponseEntity.ok("Hello super admin");
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello auth user");
    }
}
