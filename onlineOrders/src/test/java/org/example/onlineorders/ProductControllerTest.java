package org.example.onlineorders;

import org.example.onlineorders.controller.ProductController;
import org.example.onlineorders.entity.Product;
import org.example.onlineorders.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService service;
    private Product product;
    private Product product2;

    @Test
    public void testCreateProduct() {
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        when(service.create(product)).thenReturn(product);
        ResponseEntity<Product> response = productController.createProduct(product);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetAllProducts() {
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();

        product2 = Product.builder()
                .id(2L)
                .name("Product Name 2")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        when(service.findAll()).thenReturn(List.of(product, product2));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(List.of(product, product2), response.getBody());
    }

    @Test
    public void testFindById(){
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        when(service.findById(1L)).thenReturn(product);
        ResponseEntity<Product> response = productController.getProductById(product.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testFindByName(){
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        when(service.findByName("Product Name")).thenReturn(product);
        ResponseEntity<Product> response = productController.getProductByName("Product Name");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testDeleteProduct(){
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        doNothing().when(service).delete(product.getId());
        ResponseEntity<String> response = productController.deleteProduct(product.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product deleted", response.getBody());
    }

    @Test
    public void testUpdateProduct(){
        MockitoAnnotations.initMocks(this);
        product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .quantityInStock(3)
                .price(new BigDecimal(300))
                .build();
        doNothing().when(service).update(eq(product.getId()),any(Product.class));
        ResponseEntity<String> response = productController.updateProduct(product.getId(), product);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product updated", response.getBody());
    }
}
