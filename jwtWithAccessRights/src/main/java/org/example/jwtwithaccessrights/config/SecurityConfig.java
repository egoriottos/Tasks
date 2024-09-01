package org.example.jwtwithaccessrights.config;

import lombok.RequiredArgsConstructor;
import org.example.jwtwithaccessrights.handler.CustomAuthSuccessHandler;
import org.example.jwtwithaccessrights.handler.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthSuccessHandler customAuthSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers("user/moderator").hasRole("MODERATOR")
                            .requestMatchers("user/admin").hasRole("ADMIN")
                            .requestMatchers("user/hello").authenticated()
                            .anyRequest().permitAll();
                })
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form->
                        form
                                .successHandler(customAuthSuccessHandler)
                                .failureHandler(customAuthenticationFailureHandler))
                .build();
    }
}
