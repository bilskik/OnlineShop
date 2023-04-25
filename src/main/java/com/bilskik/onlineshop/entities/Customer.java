package com.bilskik.onlineshop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "CUSTOMER_TBL")
public class Customer extends User {
    @Id
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_generator",
            strategy = GenerationType.SEQUENCE
    )
    public int customerId;
}
