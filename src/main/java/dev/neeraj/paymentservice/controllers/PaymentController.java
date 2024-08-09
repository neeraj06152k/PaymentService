package dev.neeraj.paymentservice.controllers;

import com.stripe.exception.StripeException;
import dev.neeraj.paymentservice.dtos.InitiatePaymentDTO;
import dev.neeraj.paymentservice.dtos.OrderAmountDTO;
import dev.neeraj.paymentservice.dtos.OrderStatus;
import dev.neeraj.paymentservice.exceptions.OrderNotFound;
import dev.neeraj.paymentservice.services.PaymentService;
import dev.neeraj.paymentservice.services.StripePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {

    PaymentService paymentService;
    RestTemplate restTemplate;

    @Autowired
    public PaymentController(@Qualifier("stripePaymentService") PaymentService paymentService,
                             RestTemplate restTemplate) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/generatePaymentLink")
    public ResponseEntity<String> generatePaymentLink(@RequestParam("orderId") long orderId)
            throws StripeException, OrderNotFound {

        long amount = getOrderAmount(orderId);
        return ResponseEntity.ok(
                paymentService.generatePaymentLink(orderId, amount));
    }

    private long getOrderAmount(long orderId) throws OrderNotFound {
        //String url = System.getenv("ORDER_SERVICE_GET_AMOUNT_URL") + orderId;

        OrderAmountDTO orderAmountDTO = restTemplate.getForObject(
                "http://localhost:7070/order/getOrderAmount/" + orderId, OrderAmountDTO.class);

        if(orderAmountDTO==null)
            throw new OrderNotFound("Order with ID " + orderId + " not found");

        return orderAmountDTO.getAmount();
    }

    @GetMapping("/confirmPayment")
    public ResponseEntity<String> confirmPayment(@RequestParam("orderId") long orderId) {
        restTemplate.postForObject(
                "http://localhost:7070/order/updateOrderStatus",
                new OrderStatus(orderId, "PAYMENTCOMPLETED"),
                OrderStatus.class);

        return ResponseEntity.ok("Payment for order " + orderId + " successful");
    }

}
