package org.example.onlineorders.service;

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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        Product newProduct = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantityInStock(product.getQuantityInStock())
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
    public void update(Long id,Product product) {
        Product oldProduct = findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setQuantityInStock(product.getQuantityInStock());
        productRepository.save(oldProduct);
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
