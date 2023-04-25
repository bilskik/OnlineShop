package com.bilskik.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class JwtExceptionHandler {
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ErrorResponse> handleJwtException(JwtException jwtException) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),jwtException.getMessage());
//        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
//    }
//}
