package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.exception.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExampleAdvice {
    @ExceptionHandler(JwtException.class)
    public void handleException(RuntimeException e) {

    }
}
