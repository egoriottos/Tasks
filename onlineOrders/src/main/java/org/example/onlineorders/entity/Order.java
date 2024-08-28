package org.example.onlineorders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.onlineorders.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String shippingAddress;
    @Positive
    private BigDecimal totalPrice;
    private Status status;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Product> products;
    private LocalDateTime orderDate;
}
