package com.bilskik.onlineshop.embedded;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ProductDetails {
    private String details;
    private String description;
}
