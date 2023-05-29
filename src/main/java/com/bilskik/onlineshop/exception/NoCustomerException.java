package com.bilskik.onlineshop.exception;

import java.util.NoSuchElementException;

public class NoCustomerException extends NoSuchElementException {
    public NoCustomerException(String message) {
        super(message);
    }
}
