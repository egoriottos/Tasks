package org.example.onlineorders;

import org.example.onlineorders.controller.OrderController;
import org.example.onlineorders.entity.Customer;
import org.example.onlineorders.entity.Order;
import org.example.onlineorders.entity.Product;
import org.example.onlineorders.entity.enums.Status;
import org.example.onlineorders.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    private Order order;
    private Customer customer;
    private Product product1;
    private Product product2;

    @Test
    public void testCreateOrder() {
        MockitoAnnotations.openMocks(this);
        product1 = Product.builder()
                .id(1L)
                .quantityInStock(1)
                .name("cookies")
                .description("ggkfk")
                .price(new BigDecimal(500))
                .build();
        product2 = Product.builder()
                .id(2L)
                .quantityInStock(1)
                .name("cheese")
                .description("ggkfk")
                .price(new BigDecimal(500))
                .build();

        customer = Customer.builder()
                .id(1L)
                .phone("+79531728296")
                .email("egor@mail.ru")
                .firstname("john")
                .lastname("doe")
                .build();
        order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(Status.CREATED)
                .shippingAddress("Moscow, Sovetskaya str, h.50")
                .totalPrice(new BigDecimal(1000))
                .products(List.of(product1,product2))
                .customer(customer)
                .build();
        when(orderService.createOrder(order,customer.getId())).thenReturn(order);
        ResponseEntity<Order> response = orderController.createOrder(order,customer.getId());
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(order,response.getBody());
    }

    @Test
    public void testFindById(){
        MockitoAnnotations.openMocks(this);
        product1 = Product.builder()
                .id(1L)
                .quantityInStock(1)
                .name("cookies")
                .description("ggkfk")
                .price(new BigDecimal(500))
                .build();
        product2 = Product.builder()
                .id(2L)
                .quantityInStock(1)
                .name("cheese")
                .description("ggkfk")
                .price(new BigDecimal(500))
                .build();

        customer = Customer.builder()
                .id(1L)
                .phone("+79531728296")
                .email("egor@mail.ru")
                .firstname("john")
                .lastname("doe")
                .build();
        order = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(Status.CREATED)
                .shippingAddress("Moscow, Sovetskaya str, h.50")
                .totalPrice(new BigDecimal(1000))
                .products(List.of(product1,product2))
                .customer(customer)
                .build();
        when(orderService.findOrderById(order.getId())).thenReturn(order);
        ResponseEntity<Order> response = orderController.findOrder(order.getId());
        assertEquals(200,response.getStatusCodeValue());
        assertEquals(order,response.getBody());
    }
}
