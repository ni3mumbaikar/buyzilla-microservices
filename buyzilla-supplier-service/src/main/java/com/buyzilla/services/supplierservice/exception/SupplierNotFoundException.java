package com.buyzilla.services.supplierservice.exception;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
