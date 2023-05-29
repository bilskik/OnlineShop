package com.bilskik.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int cartId;
    @JsonManagedReference(value = "product_cart")
    @OneToMany(mappedBy = "cart",orphanRemoval = true)
    private List<Product> productList;
}
