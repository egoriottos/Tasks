package org.example.crud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.order.*;
import org.example.crud.configuration.View;
import org.example.crud.entity.enums.Status;
import org.example.crud.services.interfaces.OrderServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceInterface orderService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        CreateOrderCommand command = modelMapper.map(orderRequest, CreateOrderCommand.class);
        orderService.createOrder(command);
        return ResponseEntity.ok("Order created successfully");
    }

    @PutMapping("/add/orderItem/{id}")
    public ResponseEntity<String> addOrderItem(@PathVariable Long id, @RequestParam String itemName) {
        orderService.addOrderItemToOrder(itemName, id);
        return ResponseEntity.ok("Order added successfully");
    }

    @PutMapping("/change/status/{id}")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Long id, @RequestBody OrderStatusRequest status) {
        orderService.changeOrderStatus(id, modelMapper.map(status, Status.class));
        return ResponseEntity.ok("Order status changed successfully");
    }

    @PutMapping("/change/payment/{id}")
    public ResponseEntity<String> changeOrderPayment(@PathVariable Long id, @RequestBody OrderPaymentRequest payment) {
        orderService.changePaymentMethod(id, modelMapper.map(payment, UpdateOrderCommand.class));
        return ResponseEntity.ok("Order payment changed successfully");
    }

    @GetMapping("/find/all")
    @JsonView(View.Public.class)
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        List<OrderResponse> list = orderService.findAll()
                .stream()
                .map(obj -> modelMapper.map(obj, OrderResponse.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("find/{id}")
    @JsonView(View.Public.class)
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(orderService.findById(id),OrderResponse.class));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        orderService.update(id,modelMapper.map(orderRequest, UpdateOrderCommand.class));
        return ResponseEntity.ok("Order updated successfully");
    }

}
