package org.example.crud.commands.orderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemCommand {
    private String itemName;
    private String itemDescription;
    private Integer quantity;
    private BigDecimal price;
}
