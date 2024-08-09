package dev.neeraj.paymentservice.exceptions;

public class OrderNotFound extends Exception{
    public OrderNotFound(String message) {
        super(message);
    }
}
