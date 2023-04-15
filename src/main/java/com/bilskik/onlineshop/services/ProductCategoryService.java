package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    public ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    public List<Product> getAllProductsWithGivenCategory(String category) {
        Optional<ProductCategory> category1 = productCategoryRepository.findByCategory(category);
        return category1.map(ProductCategory::getProductList).orElse(null);
    }
}
