package com.bilskik.onlineshop.seed;


import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.Role;
import com.bilskik.onlineshop.repositories.CartRepository;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        customerRepository.deleteAll();
        loadData();
    }

    private void loadData() {
        Cart cart = loadCart();
        loadProduct(cart);
        loadCustomer(cart);
    }

    private Cart loadCart() {
        Cart cart = Cart.builder()
                .cartId(1)
                .productList(new ArrayList<>())
                .build();
        return cart;
    }

    private void loadProduct(Cart cart) {
        //not added details and product category
        Product product1 = Product.builder()
                .productId(1)
                .productName("Gazeta")
                .amount(1)
                .price(10)
                .cart(cart)
                .build();
        Product product2 = Product.builder()
                .productId(2)
                .productName("Ksiazka")
                .amount(1)
                .price(10)
                .cart(cart)
                .build();
        Product product3 = Product.builder()
                .productId(3)
                .productName("Pismo")
                .amount(1)
                .price(12)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

    }

    private void loadCustomer(Cart cart) {

        Customer customer = Customer.builder()
                .customerId(1)
                .name("Kamil")
                .surename("Bilski")
                .email("kamil@gmail.com")
                .gender("mezczyzna")
                .dateOfBirth(LocalDate.of(2002, 7, 5))
                .role(Role.CUSTOMER)
                .password("123")
                .cart(cart)
                .build();
        customerRepository.save(customer);
    }
}
