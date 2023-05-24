package com.bilskik.onlineshop.dto;

import com.bilskik.onlineshop.embedded.ProductDetails;
import com.bilskik.onlineshop.entities.ProductCategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int productId;
    private String productName;
    private int amount;
    private int cartItemsAmount;
    private String image;
    private double price;
    private ProductDetails productDetails;
}
