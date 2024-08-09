package dev.neeraj.paymentservice.services;

import com.stripe.exception.StripeException;

public interface PaymentService {
    public String generatePaymentLink(long orderId, long amount) throws StripeException;
}
