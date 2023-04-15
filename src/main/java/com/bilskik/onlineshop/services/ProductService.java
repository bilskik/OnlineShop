package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Product;
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
        for (Product product : list) {
            System.out.println(product);
        }
        return list;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
