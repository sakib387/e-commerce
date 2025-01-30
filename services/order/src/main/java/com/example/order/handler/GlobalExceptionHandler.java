package com.example.order.handler;

import com.example.order.exception.CustomerNotFound;
import com.example.order.exception.OrderCreationException;
import com.example.order.exception.OrderNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderCreationException.class)
    public ResponseEntity<String> handle(OrderCreationException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handle(CustomerNotFound exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handle(OrderNotFoundException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }
}
