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
    @GetMapping("/{customerId}/basket")
    public ResponseEntity<Cart> getBasketFromCustomer(@PathVariable int customerId) {
        return new ResponseEntity<>(cartService.getCartWithGivenId(customerId), HttpStatusCode.valueOf(200));
    }
    @PostMapping(path = "/customers/{customerId}/basket")
    public ResponseEntity<Product> addProductToBasket(@PathVariable int customerId, @RequestBody Product product) {
        return new ResponseEntity<>(cartService.addProductToCart(product,customerId), HttpStatusCode.valueOf(200));
    }
}
