package com.bilskik.onlineshop.controllers;
import com.bilskik.onlineshop.dto.CartDTO;
import com.bilskik.onlineshop.dto.ProductDTO;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    public CartService cartService;
    @GetMapping
    public ResponseEntity<CartDTO> getCartFromCustomer() {
        return new ResponseEntity<>(cartService.getAllProductsFromCart(), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ProductDTO> addProductToCart(@RequestBody Map<String,Integer> request) {
        int product = request.get("productId");
        return new ResponseEntity<>(cartService.addProductToCart(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/{product}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable int product) {
        cartService.deleteProductFromCart(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
