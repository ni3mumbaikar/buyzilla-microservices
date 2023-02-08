package com.buyzilla.services.customerservice.excception;

public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
