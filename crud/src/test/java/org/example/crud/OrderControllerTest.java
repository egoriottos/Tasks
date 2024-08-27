package org.example.crud;

import org.example.crud.commands.order.*;
import org.example.crud.controller.OrderController;
import org.example.crud.entity.Order;
import org.example.crud.entity.OrderItem;
import org.example.crud.entity.User;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;
import org.example.crud.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private OrderController orderController;

    private User user;
    private OrderRequest orderRequest;
    private OrderResponse orderResponse;
    private CreateOrderCommand createOrderCommand;
    private UpdateOrderCommand updateOrderCommand;
    private OrderStatusRequest orderStatusRequest;
    private OrderPaymentRequest orderPaymentRequest;

    @Test
    public void testCreateOrder() {
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
                .items(List.of(item1, item2))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        when(modelMapper.map(orderRequest, CreateOrderCommand.class)).thenReturn(createOrderCommand);

        ResponseEntity<String> response = orderController.createOrder(orderRequest);

        verify(orderService).createOrder(createOrderCommand);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order created successfully", response.getBody());
    }

    @Test
    public void testAddOrderItem() {
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        ResponseEntity<String> response = orderController.addOrderItem(1L, "milk");

        verify(orderService).addOrderItemToOrder("milk", 1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order added successfully", response.getBody());
    }

    @Test
    public void testChangeOrderStatus() {
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        orderStatusRequest = new OrderStatusRequest();

        when(modelMapper.map(orderStatusRequest, Status.class)).thenReturn(Status.PENDING);
        ResponseEntity<String> response = orderController.changeOrderStatus(1L, orderStatusRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order status changed successfully", response.getBody());
    }

    @Test
    public void testChangePayment() {
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        orderPaymentRequest = new OrderPaymentRequest();
        when(modelMapper.map(orderPaymentRequest, PaymentMethod.class)).thenReturn(PaymentMethod.DEBIT_CARD);
        ResponseEntity<String> response = orderController.changeOrderPayment(1L, orderPaymentRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order payment changed successfully", response.getBody());
    }

    @Test
    public void testFindAllOrders() {
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        when(orderService.findAll()).thenReturn(List.of(order));
        when(modelMapper.map(any(Order.class), eq(OrderResponse.class))).thenReturn(orderResponse);
        ResponseEntity<List<OrderResponse>> response = orderController.findAllOrders();
        verify(orderService).findAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testFindOrderById(){
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        when(orderService.findById(1L)).thenReturn(order);
        when(modelMapper.map(any(Order.class), eq(OrderResponse.class))).thenReturn(orderResponse);
        ResponseEntity<OrderResponse> response = orderController.findOrderById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getItems().size());
    }

    @Test
    public void testDeleteById(){
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());
        ResponseEntity<String> response = orderController.deleteOrder(1L);
       verify(orderService).delete(1L);
       assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order deleted successfully", response.getBody());
    }
    @Test
    public void testUpdateOrder(){
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
                .items(List.of(item1))
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

        orderRequest = new OrderRequest();
        orderRequest.setItems(List.of(item1, item2));
        orderRequest.setStatus(Status.PENDING);
        orderRequest.setUserEmail(user.getEmail());
        orderRequest.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        orderResponse = new OrderResponse();
        orderResponse.setTotal(order.getTotal());
        orderResponse.setItems(order.getItems());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setUser(order.getUser());

        updateOrderCommand = new UpdateOrderCommand();
        updateOrderCommand.setItems(List.of(item1, item2));
        updateOrderCommand.setStatus(Status.RETURNED);
        updateOrderCommand.setEmail(user.getEmail());
        updateOrderCommand.setPaymentMethod(PaymentMethod.DEBIT_CARD);

        when(modelMapper.map(any(OrderRequest.class),eq(UpdateOrderCommand.class))).thenReturn(updateOrderCommand);
        ResponseEntity<String> response = orderController.updateOrder(1L, orderRequest);
        verify(orderService).update(1L,updateOrderCommand);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order updated successfully", response.getBody());

    }

}
