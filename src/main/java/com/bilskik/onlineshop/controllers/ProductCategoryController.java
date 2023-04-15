package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    @Autowired
    public ProductCategoryService productCategoryService;
    @GetMapping()
    public List<ProductCategory> getCategories() {
        return productCategoryService.getAllCategories();
    }
    @GetMapping("/{category}")
    public List<Product> getAllProductsBySpecifiedCategory(@PathVariable String category) {
        System.out.println(category);
        return productCategoryService.getAllProductsWithGivenCategory(category);
    }
    @PostMapping()
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategory));
    }

}
