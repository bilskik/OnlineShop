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
@RequestMapping("/products")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteProductList(@RequestBody List<Integer> productIdList) {
        productService.deleteProductList(productIdList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
