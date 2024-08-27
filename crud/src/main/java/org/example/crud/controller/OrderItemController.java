package org.example.crud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.orderItem.CreateOrderItemCommand;
import org.example.crud.commands.orderItem.OrderItemRequest;
import org.example.crud.commands.orderItem.OrderItemResponse;
import org.example.crud.commands.orderItem.UpdateOrderItemCommand;
import org.example.crud.configuration.View;
import org.example.crud.services.interfaces.OrderItemServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemServiceInterface orderItemService;
    private final ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody OrderItemRequest request) {
        orderItemService.createOrderItem(mapper.map(request, CreateOrderItemCommand.class));
        return ResponseEntity.ok("Order item created");
    }

    @GetMapping("/all")
    @JsonView(View.Public.class)
    public ResponseEntity<List<OrderItemResponse>> getAll() {
        List<OrderItemResponse> list = orderItemService.findAll()
                .stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemResponse.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/{id}")
    @JsonView(View.Public.class)
    public ResponseEntity<OrderItemResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.map(orderItemService.findById(id), OrderItemResponse.class));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return ResponseEntity.ok("Order item deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody OrderItemRequest request) {
        orderItemService.updateOrderItem(id, mapper.map(request, UpdateOrderItemCommand.class));
        return ResponseEntity.ok("Order item updated");
    }

    @GetMapping("/findBy/name/{itemName}")
    @JsonView(View.Public.class)
    public ResponseEntity<OrderItemResponse> getByItemName(@PathVariable String itemName) {
        return ResponseEntity.ok(mapper.map(orderItemService.findByItemName(itemName), OrderItemResponse.class));
    }
}
