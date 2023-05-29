package com.bilskik.onlineshop.exception;

import java.util.NoSuchElementException;

public class NoCartException extends NoSuchElementException {
    public NoCartException(String message) {
        super(message);
    }
}
