package org.example.onlineorders.service;

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

    public Order createOrder(Order order,Long customerId) {
        Order newOrder = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .products(order.getProducts())
                .customer(customerRepository.findById(customerId).orElse(null))
                .totalPrice(getTotalPrice(order))
                .build();
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    public BigDecimal getTotalPrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(Product product : order.getProducts()) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
