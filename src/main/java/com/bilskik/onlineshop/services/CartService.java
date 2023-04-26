package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.repositories.CartRepository;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    public ProductRepository productRepository;
    public Cart getCartWithGivenId(int customerId) {
        //CartDTO?
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with given Id!");
        }
        return customer.get().cart;
    }
    //ProductDTO?
    public Product addProductToCart(Product requestProduct, int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(requestProduct.getProductId());
        if(product.isEmpty()) {
            throw new NoSuchElementException("There is no product with given Id!");
        }
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with given Id!");
        }
        Cart cart = customer.get().cart;
        product.get().setCart(cart);
        productRepository.save(product.get());
        return product.get();
    }
}
