package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        List<Product> toReturn = List.copyOf(list);
        for(int i=0; i<list.size(); i++) {
            toReturn.get(i).setProductCategory(list.get(i).getProductCategory());
        }
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i).getProductCategory().getCategory());
        }
        return toReturn;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
