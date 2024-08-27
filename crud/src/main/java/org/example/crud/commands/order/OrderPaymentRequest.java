package org.example.crud.commands.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crud.entity.enums.PaymentMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentRequest {
    private PaymentMethod paymentMethod;
}
