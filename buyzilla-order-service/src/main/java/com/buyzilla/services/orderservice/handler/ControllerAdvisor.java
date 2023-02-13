package com.buyzilla.services.orderservice.handler;//package com.buyzilla.services.buyzillashipperservice.handler;

import com.buyzilla.services.orderservice.exception.ProductNotFoundException;
import com.buyzilla.services.orderservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String>handleProductNotFound(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(productNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
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
