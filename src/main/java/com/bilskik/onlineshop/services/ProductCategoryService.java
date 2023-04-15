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
        Optional<ProductCategory> productCategory = productCategoryRepository.findByCategory(category);
        if(!productCategory.isPresent()) {
            throw new IllegalStateException(category + " is not found!");
        }
        return productCategory.get().getProductList();
    }

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
