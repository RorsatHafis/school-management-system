package com.student.demo.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation (MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult()
         .getFieldErrors()
         .forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
         );

        return ResponseEntity.status(400).body(errors);

    }

    @ExceptionHandler(InvalidSearchException.class)
    public ResponseEntity<?> handleInvalidSearch (InvalidSearchException e) {

        return ResponseEntity.badRequest().body(
            Map.of(
                "error", "INVALID_SEARCH",
                "message", e.getMessage()
            )
        );

    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleNotFound (StudentNotFoundException e) {

        return ResponseEntity.status(404).body(e.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral (Exception e) {

        e.printStackTrace();
        return ResponseEntity.status(500).body("Something went wrong!");

    }
    
}
