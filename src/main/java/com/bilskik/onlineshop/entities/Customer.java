package com.bilskik.onlineshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CUSTOMER_TBL")
public class Customer extends User {
    @Id
//    @SequenceGenerator(
//            name = "user_generator",
//            sequenceName = "user_generator",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "user_generator",
//            strategy = GenerationType.SEQUENCE
//    )
    public int customerId;
    @Builder
    public Customer(String name, String surename, String email, String gender, LocalDate dateOfBirth, String password, Role role, Cart cart, int customerId) {
        super(name,surename,email,gender,dateOfBirth,password,role);
        this.cart = cart;
        this.customerId = customerId;
    }
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "cartId",
            referencedColumnName = "cartId"
    )
    public Cart cart;

}
