package com.bilskik.onlineshop.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
//@RestControllerAdvice
//public class CustomerExceptionHandler extends ResponseEntityExceptionHandler  {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<String> errorMessages = new ArrayList<>();
//        BindingResult bindingResult = ex.getBindingResult();
//        List<ObjectError> errors = bindingResult.getAllErrors();
//        for(ObjectError error : errors) {
//            String message = error.getDefaultMessage();
//            errorMessages.add(message);
//        }
//
//        return new ResponseEntity<>(new Error((Throwable) errorMessages), new HttpHeaders(), BAD_REQUEST);
//    }
//}
