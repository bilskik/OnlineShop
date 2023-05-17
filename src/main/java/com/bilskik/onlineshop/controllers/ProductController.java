package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.dto.ProductDTO;
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
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatusCode.valueOf(200));
    }
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }
    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(240));
    }
}
