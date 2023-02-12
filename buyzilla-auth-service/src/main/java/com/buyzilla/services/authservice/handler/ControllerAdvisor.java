package com.buyzilla.services.authservice.handler;

import com.buyzilla.services.authservice.exceptions.AuthFailedException;
import com.buyzilla.services.authservice.exceptions.UserNotFoundException;
import com.buyzilla.services.authservice.exceptions.CustomerAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AuthFailedException.class)
    ResponseEntity<String> authenticationFailed(AuthFailedException authFailedException){
        return new ResponseEntity<>(authFailedException.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    ResponseEntity<String> customerAlreadyExistException(CustomerAlreadyExistException customerAlreadyExistException){
        return new ResponseEntity<>(customerAlreadyExistException.getMessage(),HttpStatus.valueOf(403));
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> customerNotFound(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        StringBuilder sb = new StringBuilder();
        methodArgumentNotValidException.getFieldErrors().forEach(err-> sb.append(err.getDefaultMessage()).append("\n"));
        return new ResponseEntity<>(sb.toString(),HttpStatus.valueOf(406));
    }

    @ExceptionHandler(SQLException.class)
    ResponseEntity<String> HsqlDb(SQLException hsqlException){
        return new ResponseEntity<>(hsqlException.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
