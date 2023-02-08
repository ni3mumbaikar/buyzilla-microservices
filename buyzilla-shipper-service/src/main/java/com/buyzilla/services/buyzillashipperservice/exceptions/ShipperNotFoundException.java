package com.buyzilla.services.buyzillashipperservice.exceptions;

public class ShipperNotFoundException extends RuntimeException{
    public ShipperNotFoundException(String message) {
        super(message);
    }
}
