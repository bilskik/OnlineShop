package com.bilskik.onlineshop.controllers;
import com.bilskik.onlineshop.dto.CartDTO;
import com.bilskik.onlineshop.dto.ProductDTO;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class CartController {
    @Autowired
    public CartService cartService;
    @GetMapping("/cart")
    public ResponseEntity<Cart> getCartFromCustomer() {
        return new ResponseEntity<>(cartService.getAllProductsFromCart(), HttpStatusCode.valueOf(200));
    }
    @PostMapping(path = "/cart")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ProductDTO> addProductToCart(@RequestBody Map<String,Integer> request) {
        int product = request.get("productId");
        return new ResponseEntity<>(cartService.addProductToCart(product), HttpStatusCode.valueOf(201));
    }
    @DeleteMapping(path = "/cart/{product}")
    public ResponseEntity<Cart> deleteProductFromCart(@PathVariable int product) {
        System.out.println("product = " + product);
        cartService.deleteProductFromCart(product);
        return new ResponseEntity<>(HttpStatusCode.valueOf(240));
    }
}
