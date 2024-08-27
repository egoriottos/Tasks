package org.example.crud.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.order.CreateOrderCommand;
import org.example.crud.commands.order.UpdateOrderCommand;
import org.example.crud.entity.Order;
import org.example.crud.entity.OrderItem;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;
import org.example.crud.repository.OrderRepository;
import org.example.crud.services.interfaces.OrderServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final UserService userService;

    @Override
    @Transactional
    public void createOrder(CreateOrderCommand command) {
        Order order = Order.builder()
                .items(Collections.emptyList())
                .total(new BigDecimal(0))
                .user(userService.findByEmail(command.getEmail()))
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .status(Status.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void addOrderItemToOrder(String itemName, Long id) {
        OrderItem orderItemToAdd = orderItemService.findByItemName(itemName);
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.getItems().add(orderItemToAdd);
        order.setTotal(order.getTotal().add(orderItemToAdd.getPrice()));
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void changeOrderStatus(Long orderId, Status status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void changePaymentMethod(Long orderId, UpdateOrderCommand paymentMethod) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setPaymentMethod(paymentMethod.getPaymentMethod());
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, UpdateOrderCommand command) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(command.getStatus());
        order.setPaymentMethod(command.getPaymentMethod());
        order.setItems(command.getItems());
        order.setTotal(calculateTotal(command.getItems()));
        order.setUser(userService.findByEmail(command.getEmail()));
        order.setUpdatedAt(LocalDateTime.now());

    }

    private BigDecimal calculateTotal(List<OrderItem> orderItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            total = total.add(orderItem.getPrice());
        }
        return total;
    }
}
