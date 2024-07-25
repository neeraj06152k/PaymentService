package dev.neeraj.paymentservice.controllers;

import dev.neeraj.paymentservice.dtos.InitiatePaymentDTO;
import dev.neeraj.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/generate-payment-link")
    public ResponseEntity<String> generatePaymentLink(@RequestBody InitiatePaymentDTO initiatePaymentDTO) {

        return ResponseEntity.ok(
                paymentService.generatePaymentLink(initiatePaymentDTO.getOrderId(),
                        initiatePaymentDTO.getAmount()));
    }

}
