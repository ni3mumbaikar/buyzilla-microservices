package com.buyzilla.services.authservice.exceptions;

public class AuthFailedException extends RuntimeException{
    public AuthFailedException(String message) {
        super( message);

    }
}
