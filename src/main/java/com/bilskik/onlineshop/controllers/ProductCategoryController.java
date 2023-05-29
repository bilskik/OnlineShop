package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {
    @Autowired
    public ProductCategoryService productCategoryService;
    @GetMapping()
    public List<ProductCategory> getCategories() {
        return productCategoryService.getAllCategories();
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<Product>> getAllProductsBySpecifiedCategory(@PathVariable String category) {
        return new ResponseEntity<>(productCategoryService.getAllProductsWithGivenCategory(category), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory productCategory) {
        return new ResponseEntity<>(productCategoryService.createProductCategory(productCategory), HttpStatus.CREATED);
    }

}
