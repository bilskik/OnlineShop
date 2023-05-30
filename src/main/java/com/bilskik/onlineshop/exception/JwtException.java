package com.bilskik.onlineshop.exception;

public class JwtException extends RuntimeException{
    public JwtException(String message, Throwable casue) {
        super(message,casue);
    }
}
