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
    public String street;
    public String city;
    public String country;
    public String zipCode;
}
