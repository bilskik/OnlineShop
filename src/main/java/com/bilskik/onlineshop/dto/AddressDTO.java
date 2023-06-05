package com.bilskik.onlineshop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
