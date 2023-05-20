package com.bilskik.onlineshop.advice;

import com.bilskik.onlineshop.exception.DuplicateEntryException;
import com.bilskik.onlineshop.exception.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(HttpServletRequest request, SQLIntegrityConstraintViolationException ex) {
        String error = "Error: " + ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST,error));
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleJwtException(HttpServletRequest request, JwtException ex) {
        String error = "Error: " + ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST,error));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.FORBIDDEN,error));
    }
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Object> handleBadCredentialsException(DuplicateEntryException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.FORBIDDEN,error));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse,errorResponse.getHttpStatus());
    }
}
