package org.example.jwtwithaccessrights.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.example.jwtwithaccessrights.entity.User;
import org.example.jwtwithaccessrights.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public String createUser(User user) {
        User createdUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRoles())
                .build();
        log.info("User created: {}", createdUser);
        userRepository.save(createdUser);
        log.info("User saved: {}", createdUser);
        return jwtService.createToken(createdUser);
    }

    public String login(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        log.info("User logged in: {}", user.getUsername());
        var findUser = userRepository.findByUsername(user.getUsername());
        log.info("User found: {}", findUser);
        var token = jwtService.createToken(findUser);
        log.info("Token created: {}", token);
        return token;
    }
}
