package com.bilskik.onlineshop.exception;

import java.util.NoSuchElementException;

public class NoOrderException extends NoSuchElementException {
    public NoOrderException(String message) {
        super(message);
    }
}
