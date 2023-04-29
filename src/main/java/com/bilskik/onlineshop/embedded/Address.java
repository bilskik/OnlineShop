package com.bilskik.onlineshop.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private int id;
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
