package org.example.crud.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.entity.OrderItem;
import org.example.crud.entity.enums.PaymentMethod;
import org.example.crud.entity.enums.Status;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderCommand {
    private List<OrderItem> items;
    private String email;
    private Status status;
    private PaymentMethod paymentMethod;
}
