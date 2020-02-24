package com.brs.library.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.ServletException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleAuthenticationServiceException(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> handleAuthenticationException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
