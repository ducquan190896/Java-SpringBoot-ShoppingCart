package com.quan.shoppingcart;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quan.shoppingcart.Exception.EntityNotFoundException;
import com.quan.shoppingcart.Exception.ErrorResponse;

@ControllerAdvice
public class ShoppingHandlingException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }
}
