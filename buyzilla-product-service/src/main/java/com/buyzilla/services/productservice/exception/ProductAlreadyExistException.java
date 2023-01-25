package com.buyzilla.services.productservice.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
