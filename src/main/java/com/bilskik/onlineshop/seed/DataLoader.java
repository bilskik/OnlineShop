package com.bilskik.onlineshop.seed;


import com.bilskik.onlineshop.embedded.ProductDetails;
import com.bilskik.onlineshop.entities.*;
import com.bilskik.onlineshop.enumeration.Role;
import com.bilskik.onlineshop.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        customerRepository.deleteAll();
        productCategoryRepository.deleteAll();
        loadData();
    }

    private void loadData() {
        Cart cart = loadCart();
        List<ProductCategory> c = createCategory();
        loadProduct(cart,c);
        loadCustomer(cart);
    }


    private Cart loadCart() {
        Cart cart = Cart.builder()
                .productList(new ArrayList<>())
                .build();
        return cart;
    }

    private void loadProduct(Cart cart, List<ProductCategory> c) {
        for(int i=0; i<10; i++) {
            Faker fakeData = new Faker();
            int sentecensDescription = 1;
            int sentencesDetails = 1;
            Product product = Product.builder()
                    .productName(fakeData.name().firstName())
                    .amount(fakeData.random().nextInt(1, 2))
                    .cartItemsAmount(0)
                    .image("https://i.ibb.co/cXJhRns/komputer.jpg")
                    .price(fakeData.random().nextInt(1, 2000))
                    .productDetails(new ProductDetails(fakeData.lorem().paragraph(sentencesDetails), fakeData.lorem().paragraph(sentecensDescription)))
                    .build();
            productRepository.save(product);
        }
    }

    private Customer loadCustomer(Cart cart) {
        Cart cart2 = Cart.builder()
                .productList(new ArrayList<>())
                .build();
        Customer customer = Customer.builder()
                .name("Kamil")
                .surename("Bilski")
                .email("test@123")
                .gender("male")
                .dateOfBirth(LocalDate.of(2002, 7, 5))
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode("123"))
                .cart(cart)
                .build();
        Customer customer2 = Customer.builder()
                .name("tescik")
                .surename("siemanko")
                .email("123@123")
                .gender("male")
                .dateOfBirth(LocalDate.of(2002, 7, 5))
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode("123"))
                .cart(cart2)
                .build();
        customerRepository.save(customer);
        customerRepository.save(customer2);
        return customer;

    }
    private List<ProductCategory> createCategory() {
        ProductCategory category1 = ProductCategory.builder()
                .categoryId(1)
                .category("Materiały Pismienne")
                .build();
        ProductCategory category2 = ProductCategory.builder()
                .categoryId(2)
                .category("RTV-AGD")
                .build();
        productCategoryRepository.save(category1);
        productCategoryRepository.save(category2);

        return List.of(category1,category2);
    }
}
