package com.bilskik.onlineshop.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Seller extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerId;
    @Builder
    public Seller(String name, String surename, String email, String gender, LocalDate dateOfBirth, String password, Role role) {
        super(name,surename,email,gender,dateOfBirth,password,role);
    }

}
