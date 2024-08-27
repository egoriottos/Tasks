package org.example.crud.services.interfaces;

import org.example.crud.commands.order.CreateOrderCommand;
import org.example.crud.commands.order.UpdateOrderCommand;
import org.example.crud.entity.Order;
import org.example.crud.entity.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServiceInterface {
    void addOrderItemToOrder(String itemName, Long id);
    void changeOrderStatus(Long orderId, Status status);
    void createOrder(CreateOrderCommand command);
    void changePaymentMethod(Long orderId, UpdateOrderCommand paymentMethod);
    List<Order> findAll();
    Order findById(Long id);
    void delete(Long id);
    void update(Long id, UpdateOrderCommand command);
}
