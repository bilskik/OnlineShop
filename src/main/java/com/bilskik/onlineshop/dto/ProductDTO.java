package com.bilskik.onlineshop.dto;

import com.bilskik.onlineshop.entities.ProductCategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    //to change(to remove productId)
    private int productId;
    private String productName;
    private int amount;
    private double price;
}
