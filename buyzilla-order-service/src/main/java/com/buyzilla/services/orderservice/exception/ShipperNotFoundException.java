package com.buyzilla.services.orderservice.exception;

public class ShipperNotFoundException extends RuntimeException{
    public ShipperNotFoundException(String message) {
        super(message);
    }
}
