package org.example.crud.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.orderItem.CreateOrderItemCommand;
import org.example.crud.commands.orderItem.UpdateOrderItemCommand;
import org.example.crud.entity.OrderItem;
import org.example.crud.repository.OrderItemRepository;
import org.example.crud.services.interfaces.OrderItemServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService implements OrderItemServiceInterface {
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void createOrderItem(CreateOrderItemCommand command) {
        OrderItem orderItem = OrderItem.builder()
                .itemName(command.getItemName())
                .itemDescription(command.getItemDescription())
                .price(command.getPrice())
                .quantity(command.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
        orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " Not Found"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateOrderItem(Long id, UpdateOrderItemCommand command) {
        OrderItem orderItem = findById(id);
        orderItem.setItemName(command.getItemName());
        orderItem.setItemDescription(command.getItemDescription());
        orderItem.setPrice(command.getPrice());
        orderItem.setQuantity(command.getQuantity());
        orderItem.setUpdatedAt(LocalDateTime.now());
        orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem findByItemName(String itemName) {
        return orderItemRepository.findByItemName(itemName);
    }
}
