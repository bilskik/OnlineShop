package com.bilskik.onlineshop.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {
    @Id
    @SequenceGenerator(
            name = "seq_category",
            sequenceName = "seq_category",
            initialValue = 1
    )
    @GeneratedValue(
            generator = "seq_category",
            strategy = GenerationType.SEQUENCE
    )
    public int categoryId;
    private String category;
    @JsonManagedReference(value = "product_category")
    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<Product> productList;

    public void addProduct(Product p) {
        if(productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(p);
        p.setProductCategory(this);
    }

}
