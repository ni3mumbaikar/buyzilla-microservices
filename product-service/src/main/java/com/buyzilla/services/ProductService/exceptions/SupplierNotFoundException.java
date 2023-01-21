package com.buyzilla.services.ProductService.exceptions;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
