package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.dto.CartDTO;
import com.bilskik.onlineshop.dto.ProductDTO;
import com.bilskik.onlineshop.dto.UserEmailDTO;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.exception.NoCartException;
import com.bilskik.onlineshop.exception.NoCustomerException;
import com.bilskik.onlineshop.exception.NoEmailException;
import com.bilskik.onlineshop.exception.NoProductException;
import com.bilskik.onlineshop.mapper.CartMapper;
import com.bilskik.onlineshop.mapper.MapperImpl;
import com.bilskik.onlineshop.repositories.CartRepository;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private MapperImpl<Product,ProductDTO> productMapper;
    @Autowired
    private CartMapper cartMapper;
    public CartDTO getAllProductsFromCart() {
        String email = userEmailDTO.getEmail();
        if(email == null) {
            throw new NoEmailException("Email doesn't exist!");
        }
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isEmpty()) {
            throw new NoCustomerException("There is no customer with given Id!");
        }
        return cartMapper.toDTO(customer.get().getCart());
    }
    public ProductDTO addProductToCart(int productId) {
        String email = userEmailDTO.getEmail();
        if(email == null) {
            throw new NoEmailException("Email doesn't exist!");
        }
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NoProductException("There is no product with given Id!");
        }
        if(customer.isEmpty()) {
            throw new NoCustomerException("There is no customer with given Id!");
        }
        Cart cart = customer.get().cart;
        if(cart == null) {
            throw new NoCartException("There is no cart with given customer!");
        }
        product.get().setCart(cart);
        productRepository.save(product.get());
        return productMapper.toDTO(product.get());
    }

    public void deleteProductFromCart(int productId) {
        String email = userEmailDTO.getEmail();
        if(email == null) {
            throw new NoEmailException("Email doesn't exist!");
        }
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NoProductException("There is no product with given ID!");
        }
        if(customer.isEmpty()) {
            throw new NoCustomerException("There is no customer with given ID!");
        }
        product.get().setCart(null);
        productRepository.save(product.get());
    }
}
