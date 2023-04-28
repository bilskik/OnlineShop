package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatusCode.valueOf(200));
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return  ResponseEntity.ok(productService.saveProduct(product));
    }
//    @PutMapping
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatusCode.valueOf(200));
    }
}
