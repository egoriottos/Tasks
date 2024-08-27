package org.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.configuration.View;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name="order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    private Long id;
    @JsonView(View.Public.class)
    @Column(nullable = false)
    @Size(min = 2, max = 25)
    private String itemName;
    @JsonView(View.Public.class)
    private String itemDescription;
    @JsonView(View.Public.class)
    @Min(value = 0,message = "cannot be less than 0")
    private Integer quantity;
    @JsonView(View.Public.class)
    @Column(nullable = false)
    @Min(1)
    @Max(300000)
    private BigDecimal price;
    @JsonView(View.Public.class)
    private LocalDateTime createdAt;
    @JsonView(View.Public.class)
    private LocalDateTime updatedAt;
}
