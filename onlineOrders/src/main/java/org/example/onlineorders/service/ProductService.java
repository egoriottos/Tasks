package org.example.onlineorders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.onlineorders.entity.Product;
import org.example.onlineorders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(String product) {
        Product productJson = null;
        try {
            productJson = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Product newProduct = Product.builder()
                .name(productJson.getName())
                .price(productJson.getPrice())
                .description(productJson.getDescription())
                .quantityInStock(productJson.getQuantityInStock())
                .build();
        productRepository.save(newProduct);
        return newProduct;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product update(Long id, String product) {
        Product jsonObj = null;
        try {
            jsonObj = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Product oldProduct = findById(id);
        oldProduct.setName(jsonObj.getName());
        oldProduct.setPrice(jsonObj.getPrice());
        oldProduct.setDescription(jsonObj.getDescription());
        oldProduct.setQuantityInStock(jsonObj.getQuantityInStock());
        productRepository.save(oldProduct);
        return oldProduct;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
