package org.example.onlineorders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<String> createOrder(@RequestBody String order,@RequestParam Long id) {
        Order order1 =orderService.createOrder(order,id);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(order1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<String> findOrder(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }
}
