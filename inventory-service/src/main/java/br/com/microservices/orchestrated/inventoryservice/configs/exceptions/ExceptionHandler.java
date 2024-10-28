package br.com.microservices.orchestrated.inventoryservice.configs.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception){


        return ResponseEntity.internalServerError().build();
    }
}
