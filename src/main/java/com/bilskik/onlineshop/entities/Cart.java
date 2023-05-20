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
    public void addProduct(Product product)  {
        if(productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
    }
    public void removeProduct(int productId) {
        if(productList == null) {
            throw new NullPointerException("ProductList is null!");
        }
        else {
            int index = 0;
            for(var product : productList) {
                if(product.getProductId() == productId) {
                    break;
                }
                index++;
            }
            productList.remove(index);
        }
        for(var product : productList) {
            System.out.println(product);
        }

    }
}
