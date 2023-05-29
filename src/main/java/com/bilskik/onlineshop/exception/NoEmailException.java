package com.bilskik.onlineshop.exception;

import java.util.NoSuchElementException;

public class NoEmailException extends NoSuchElementException {
    public NoEmailException(String message) {
        super(message);
    }
}
