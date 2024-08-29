package org.example.onlineorders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Product;
import org.example.onlineorders.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllProducts() throws JsonProcessingException {
        List<Product> products = productService.findAll();
        List<String> productNames = new ArrayList<>();
        for (Product product : products) {
            String response = new ObjectMapper().writeValueAsString(product);
            productNames.add(response);
        }
        return ResponseEntity.ok(productNames);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody String product) {
        Product product1 = productService.create(product);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(product1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id)  {
        Product product1 = productService.findById(id);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(product1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<String> getProductByName(@PathVariable String name) {
        Product product1 = productService.findByName(name);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(product1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody String product) {
        Product product1 = productService.update(id, product);
        String response = null;
        try {
            response = new ObjectMapper().writeValueAsString(product1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted");
    }

}
