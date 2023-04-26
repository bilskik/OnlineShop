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
        loadData();
    }

    private void loadData() {
        Cart cart = loadCart();
        loadCustomer();
        loadProduct();
    }

    private Cart loadCart() {
            return null;
    }

    private void loadProduct() {
        //not added details and product category
        Product product1 = Product.builder()
                .productName("Gazeta")
                .amount(1)
                .price(10)
                .build();
        Product product2 = Product.builder()
                .productName("Ksiazka")
                .amount(1)
                .price(10)
                .build();
        Product product3 = Product.builder()
                .productName("Pismo")
                .amount(1)
                .price(12)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

    }

    private void loadCustomer() {
        Cart cart = Cart.builder()
                .productList(new ArrayList<>())
                .build();
        Customer customer = Customer.builder()
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
