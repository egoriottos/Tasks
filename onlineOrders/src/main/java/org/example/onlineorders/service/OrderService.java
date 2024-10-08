package org.example.onlineorders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Order;
import org.example.onlineorders.entity.Product;
import org.example.onlineorders.repository.CustomerRepository;
import org.example.onlineorders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public Order createOrder(String order, Long customerId)  {
        Order orderObj = null;
        try {
            orderObj = objectMapper.readValue(order, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Order newOrder = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(orderObj.getStatus())
                .shippingAddress(orderObj.getShippingAddress())
                .products(orderObj.getProducts())
                .customer(customerRepository.findById(customerId).orElse(null))
                .totalPrice(getTotalPrice(orderObj))
                .build();
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public BigDecimal getTotalPrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : order.getProducts()) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
