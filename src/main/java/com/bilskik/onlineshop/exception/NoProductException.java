package com.bilskik.onlineshop.exception;

import java.util.NoSuchElementException;

public class NoProductException extends NoSuchElementException {
    public NoProductException(String message) {
        super(message);
    }
}
