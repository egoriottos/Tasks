package org.example.crud.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.entity.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusRequest {
    private Status status;

}
