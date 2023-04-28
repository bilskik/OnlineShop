package com.bilskik.onlineshop.controllers;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    public CartService cartService;
    @GetMapping("/customers/{customerId}/cart")
    public ResponseEntity<Cart> getCartFromCustomer(@PathVariable int customerId) {
        return new ResponseEntity<>(cartService.getAllProductsFromCart(customerId), HttpStatusCode.valueOf(200));
    }
    @PostMapping(path = "/customers/{customerId}/cart")
    public ResponseEntity<Product> addProductToCart(@PathVariable int customerId, @RequestBody Product product) {
        return new ResponseEntity<>(cartService.addProductToCart(product,customerId), HttpStatusCode.valueOf(200));
    }
    @DeleteMapping(path = "/customers/{customerId}/cart/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable int customerId, @PathVariable int productId) {
        cartService.deleteProductFromCart(customerId,productId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(240));
    }
}
