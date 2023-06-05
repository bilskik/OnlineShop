package com.bilskik.onlineshop.entities;

import com.bilskik.onlineshop.embedded.ProductDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
            strategy = GenerationType.IDENTITY
    )
    public int productId;
    public String productName;
    public int amount;
    public String image;
    public int cartItemsAmount;
    public double price;
    @JsonBackReference(value = "product_category")
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_product_id",
            referencedColumnName = "categoryId"
    )
    public ProductCategory productCategory;

    @JsonBackReference(value = "product_cart")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "cartId",
            referencedColumnName = "cartId"
    )
    public Cart cart;
    @Embedded
    public ProductDetails productDetails;

}
