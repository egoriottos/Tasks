package org.example.onlineorders.controller;

import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Order;
import org.example.onlineorders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order,@RequestParam Long id) {
        return ResponseEntity.ok(orderService.createOrder(order, id));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }
}
