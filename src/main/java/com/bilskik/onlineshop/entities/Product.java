package com.bilskik.onlineshop.entities;

import com.bilskik.onlineshop.entities.embedded.ProductDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @SequenceGenerator(
            name = "seq_product",
            sequenceName = "seq_product",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "seq_product",
            strategy = GenerationType.SEQUENCE
    )
    private int productId;
    private String productName;
    private int amount;
    private double price;
    @JsonManagedReference
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "category_product_id",
            referencedColumnName = "categoryId"
    )
    private ProductCategory productCategory;
    @Embedded
    private ProductDetails productDetails;

}
