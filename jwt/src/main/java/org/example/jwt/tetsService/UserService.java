package org.example.jwt.tetsService;

import lombok.RequiredArgsConstructor;
import org.example.jwt.entity.User;
import org.example.jwt.entity.enums.Roles;
import org.example.jwt.jwtService.JwtService;
import org.example.jwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String createUser(User user) {
        User savedUser = User.builder()
                .role(user.getRole())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(savedUser);
        var token = jwtService.generateToken(savedUser);
        return token;
    }

    public String auth(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        var findUser = userRepository.findByUsername(user.getUsername());
        var token = jwtService.generateToken(findUser);
        return token;

    }
}
