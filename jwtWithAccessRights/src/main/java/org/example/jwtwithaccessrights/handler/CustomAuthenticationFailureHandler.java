package org.example.jwtwithaccessrights.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.jwtwithaccessrights.entity.User;
import org.example.jwtwithaccessrights.repository.UserRepository;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final UserRepository userRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setFailedAttempts(user.getFailedAttempts() + 1);

            if (user.getFailedAttempts() >= 5) {
                user.setLockTime(LocalDateTime.now().plusMinutes(15)); // блокируем на 15 минут
                user.setEnabled(false);
            }

            userRepository.save(user);
        }

    }
}
