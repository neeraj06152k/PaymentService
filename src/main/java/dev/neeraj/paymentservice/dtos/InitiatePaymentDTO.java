package dev.neeraj.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDTO {
    private long orderId;
    private long amount;
}
