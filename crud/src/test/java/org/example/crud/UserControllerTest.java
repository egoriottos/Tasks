package org.example.crud;

import org.example.crud.commands.user.*;
import org.example.crud.controller.UserController;
import org.example.crud.entity.Order;
import org.example.crud.entity.OrderItem;
import org.example.crud.entity.User;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;
import org.example.crud.services.interfaces.UserServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserServiceInterface userService;
    @InjectMocks
    private UserController userController;
    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private UserResponseWithInfo userResponseWithInfo;

    @Test
    public void testCreateUser(){
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");

        when(modelMapper.map(any(UserRequest.class), eq(CreateUserCommand.class)))
                .thenReturn(new CreateUserCommand());
        when(userService.createUser(any(CreateUserCommand.class)))
                .thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserResponse.class)))
                .thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = userController.createUser(userRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(userResponse, responseEntity.getBody());

    }
    @Test
    public void testGetAllUsers(){
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");

        when(userService.getAllUsers())
                .thenReturn(Arrays.asList(user));
        when(modelMapper.map(any(User.class), eq(UserResponse.class)))
                .thenReturn(userResponse);

        ResponseEntity<List<UserResponse>> responseEntity = userController.getAllUsers();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(userResponse, responseEntity.getBody().get(0));

    }

    @Test
    public void testGetUserById(){
        MockitoAnnotations.openMocks(this);
        OrderItem item1 = OrderItem.builder()
                .id(1L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("beer")
                .createdAt(LocalDateTime.now())
                .build();
        OrderItem item2 = OrderItem.builder()
                .id(2L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("milk")
                .createdAt(LocalDateTime.now())
                .build();
        Order order = Order.builder()
                .id(1L)
                .total(new BigDecimal(1000))
                .items(List.of(item1,item2))
                .status(Status.PENDING)
                .user(user)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .createdAt(LocalDateTime.now())
                .build();

        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .orders(List.of(order))
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");
        userResponseWithInfo.setOrders(List.of(order));

        when(userService.getUserInfo(anyLong())).thenReturn(user);
        when(modelMapper.map(eq(user), eq(UserResponseWithInfo.class))).thenReturn(userResponseWithInfo);
        ResponseEntity<UserResponseWithInfo> response = userController.getUserInfo(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userResponseWithInfo, response.getBody());
    }

    @Test
    public void testUpdateUser(){
        MockitoAnnotations.openMocks(this);
        OrderItem item1 = OrderItem.builder()
                .id(1L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("beer")
                .createdAt(LocalDateTime.now())
                .build();
        OrderItem item2 = OrderItem.builder()
                .id(2L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("milk")
                .createdAt(LocalDateTime.now())
                .build();
        Order order = Order.builder()
                .id(1L)
                .total(new BigDecimal(1000))
                .items(List.of(item1,item2))
                .status(Status.PENDING)
                .user(user)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .createdAt(LocalDateTime.now())
                .build();

        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .orders(List.of(order))
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");
        userResponseWithInfo.setOrders(List.of(order));

        doNothing().when(userService).updateUser(anyLong(), any(UpdateUserCommand.class));

        ResponseEntity<String> responseEntity = userController.updateUser(1L, userRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("User updated with id 1 email test@example.com", responseEntity.getBody());

    }

    @Test
    public void testDeleteUser(){
        MockitoAnnotations.openMocks(this);
        OrderItem item1 = OrderItem.builder()
                .id(1L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("beer")
                .createdAt(LocalDateTime.now())
                .build();
        OrderItem item2 = OrderItem.builder()
                .id(2L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("milk")
                .createdAt(LocalDateTime.now())
                .build();
        Order order = Order.builder()
                .id(1L)
                .total(new BigDecimal(1000))
                .items(List.of(item1,item2))
                .status(Status.PENDING)
                .user(user)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .createdAt(LocalDateTime.now())
                .build();

        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .orders(List.of(order))
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");
        userResponseWithInfo.setOrders(List.of(order));

        doNothing().when(userService).deleteUser(anyLong());
        ResponseEntity<String> responseEntity = userController.deleteUser(1L);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("User deleted with id 1", responseEntity.getBody());
    }
    @Test
    public void testFindByEmail(){
        MockitoAnnotations.openMocks(this);
        OrderItem item1 = OrderItem.builder()
                .id(1L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("beer")
                .createdAt(LocalDateTime.now())
                .build();
        OrderItem item2 = OrderItem.builder()
                .id(2L)
                .itemDescription("aaa")
                .price(new BigDecimal(500))
                .quantity(1)
                .itemName("milk")
                .createdAt(LocalDateTime.now())
                .build();
        Order order = Order.builder()
                .id(1L)
                .total(new BigDecimal(1000))
                .items(List.of(item1,item2))
                .status(Status.PENDING)
                .user(user)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .createdAt(LocalDateTime.now())
                .build();

        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test User")
                .orders(List.of(order))
                .build();

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");

        userResponseWithInfo = new UserResponseWithInfo();
        userResponseWithInfo.setEmail("test@example.com");
        userResponseWithInfo.setName("Test User");
        userResponseWithInfo.setOrders(List.of(order));

        when(userService.findByEmail(anyString())).thenReturn(user);
        when(modelMapper.map(eq(user), eq(UserResponseWithInfo.class))).thenReturn(userResponseWithInfo);
        ResponseEntity<UserResponseWithInfo> responseEntity = userController.getUserByEmail("test@example.com");
        assertEquals(200,responseEntity.getStatusCodeValue());
        assertEquals(userResponseWithInfo, responseEntity.getBody());
    }
}
