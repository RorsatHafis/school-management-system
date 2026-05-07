package com.student.demo.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.java.com.student.demo.exception.UsernameAlreadyTakenException;
import main.java.com.student.demo.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation (MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult()
         .getFieldErrors()
         .forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
         );

        return ResponseEntity.status(400).body(new ApiResponse<>(false, "Validation failed", errors));

    }

    @ExceptionHandler(InvalidSearchException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidSearch(InvalidSearchException e) {

        return ResponseEntity.badRequest().body(
            new ApiResponse<>(false, e.getMessage(), null)
        );
        
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound (StudentNotFoundException e) {

        return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));

    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ApiResponse<?>> handleTakenUsername (UsernameAlreadyTakenException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneral (Exception e) {

        e.printStackTrace();
        return ResponseEntity.status(500).body(ApiResponse.error(e.getMessage()));

    }
    
}
