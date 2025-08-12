package com.shop.ShopApplication.Exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Missing Values", errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e){
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "No resource found", Map.of(e.getMessage(), e.status));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorMessage> handleExpiredJwtException(){
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Expired Refresh Token", Map.of("refresh_token", "Invalid"));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorMessage> handleMalformedJwtException(){
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Invalid Refresh Token", Map.of("refresh_token", "Invalid"));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorMessage> handleSignatureException(){
        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Invalid Signature", Map.of("Signature", "Invalid"));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleClassCastException(IllegalArgumentException e){
        if(e.getMessage().contains("Unsupported Role")){
            ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Invalid Role", Map.of("Role", "Not Supported"));
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), e.getMessage(), Map.of("Message", "Not Handled"));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
