package com.bilskik.onlineshop.dto;

import com.bilskik.onlineshop.entities.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    //to change(to remove productId)
    private int productId;
    private String productName;
    private int amount;
    private double price;
    private ProductCategory productCategory;
}
