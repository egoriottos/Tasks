package org.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.configuration.View;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;

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
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    @Min(0)
    @Max(100000000)
    private BigDecimal total;
    @OneToMany
    @JsonView(View.Internal.class)
    private List<OrderItem> items;
    @ManyToOne
    @JsonView(View.Public.class)
    @Column(nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @JsonView(View.Public.class)
    private Status status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @JsonView(View.Public.class)
    private LocalDateTime updatedAt;
    @JsonView(View.Public.class)
    private LocalDateTime createdAt;
}
