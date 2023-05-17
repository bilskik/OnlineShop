package com.bilskik.onlineshop.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
