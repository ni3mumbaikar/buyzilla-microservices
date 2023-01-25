package com.buyzilla.services.productservice.exception;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
