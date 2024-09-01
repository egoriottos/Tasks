package org.example.jwtwithaccessrights.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.jwtwithaccessrights.entity.User;
import org.example.jwtwithaccessrights.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter("username");
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setFailedAttempts(0);
            userRepository.save(user);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
