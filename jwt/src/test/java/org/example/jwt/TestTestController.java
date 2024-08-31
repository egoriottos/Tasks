package org.example.jwt;

import org.example.jwt.controller.TestController;
import org.example.jwt.entity.User;
import org.example.jwt.entity.enums.Roles;
import org.example.jwt.jwtService.JwtService;
import org.example.jwt.tetsService.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@WebMvcTest(TestController.class)
public class TestTestController {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TestController testController;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreate() throws Exception {
        User user = User.builder()
                .id(1L)
                .role(Roles.ADMIN)
                .username("egor")
                .password("123")
                .build();

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZ29yIiwiaWF0IjoxNzI1MTQ0NDYzLCJleHAiOjE3MjUyMzA4NjN9.-x2kCotBwq_7F-p5U0_WTyE--K4KWLN79t_FkAWAA7Q";

        UserRequest request = new UserRequest();
        request.setUsername("egor");
        request.setPassword("123");
        request.setRole(Roles.ADMIN);

        when(modelMapper.map(request, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(token);

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"egor\",\"password\":\"123\",\"role\":\"ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }
}
