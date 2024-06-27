package com.foroHub.infrasctructure.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
//        Map<String, String> error = new HashMap<>();
//        e.getBindingResult().getFieldErrors().forEach(errorField -> {
//            error.put(errorField.getField(), errorField.getDefaultMessage());
//        });
//        return ResponseEntity.status(400).body(error);

            return ResponseEntity.badRequest().body(
                    e.getFieldErrors().stream()
                            .map(Error::new)
                            .toList()
            );
    }

    private record Error(String field, String message) {
        public Error(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
