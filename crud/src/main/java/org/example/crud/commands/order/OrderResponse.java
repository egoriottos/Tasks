package org.example.crud.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.entity.OrderItem;
import org.example.crud.entity.User;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private BigDecimal total;
    private List<OrderItem> items;
    private User user;
    private Status status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
