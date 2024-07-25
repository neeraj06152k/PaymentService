package dev.neeraj.paymentservice.services;

public interface PaymentService {
    public String generatePaymentLink(String orderId, double amount);
}
