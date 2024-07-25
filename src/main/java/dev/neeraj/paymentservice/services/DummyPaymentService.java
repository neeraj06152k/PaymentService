package dev.neeraj.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class DummyPaymentService implements PaymentService {
    @Override
    public String generatePaymentLink(String orderId, double amount) {
        return "https://dummy-payment-link.dummy?orderId=" + orderId + "&amount=" + amount;
    }
}
