package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.dto.UserEmailDTO;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.User;
import com.bilskik.onlineshop.repositories.CartRepository;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    @Autowired
    private UserEmailDTO userEmailDTO;
    public Cart getAllProductsFromCart() {
        String email = userEmailDTO.getEmail();
        //CartDTO?
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with given Id!");
        }
        return customer.get().cart;
    }
    //ProductDTO?
    public Product addProductToCart(int productId) {
        String email = userEmailDTO.getEmail();
        if(email == null) {
            throw new NoSuchElementException("Email is not proper!");
        }
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Optional<Product> product = productRepository.findById(productId);
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

    public String deleteProductFromCart(int customerId, int productId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NoSuchElementException("There is no product with given ID!");
        }
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with given ID!");
        }
        product.get().setCart(null);
        productRepository.save(product.get());
        return "Deleted properly!";
    }
}
