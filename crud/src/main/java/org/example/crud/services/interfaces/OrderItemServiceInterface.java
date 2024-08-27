package org.example.crud.services.interfaces;

import org.example.crud.commands.orderItem.CreateOrderItemCommand;
import org.example.crud.commands.orderItem.UpdateOrderItemCommand;
import org.example.crud.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemServiceInterface {
    void createOrderItem(CreateOrderItemCommand command);
    List<OrderItem> findAll();
    OrderItem findById(Long id);
    void deleteById(Long id);
    void updateOrderItem(Long id, UpdateOrderItemCommand command);
    OrderItem findByItemName(String itemName);
}
