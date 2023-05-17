package com.bilskik.onlineshop.seed;


import com.bilskik.onlineshop.embedded.Address;
import com.bilskik.onlineshop.embedded.ProductDetails;
import com.bilskik.onlineshop.entities.*;
import com.bilskik.onlineshop.enumeration.OrderStatus;
import com.bilskik.onlineshop.enumeration.Role;
import com.bilskik.onlineshop.repositories.CartRepository;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.OrderRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
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
    private OrderRepository orderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        customerRepository.deleteAll();
        loadData();
    }

    private void loadData() {
        Cart cart = loadCart();
        List<ProductCategory> c = createCategory();
        loadProduct(cart,c);
        Customer customer = loadCustomer(cart);
        loadOrder(customer,cart);
    }


    private Cart loadCart() {
        Cart cart = Cart.builder()
                .cartId(1)
                .productList(new ArrayList<>())
                .build();
        return cart;
    }

    private void loadProduct(Cart cart, List<ProductCategory> c) {
        //not added details and product category
        Product product1 = Product.builder()
                .productId(1)
                .productName("Gazeta")
                .amount(10)
                .cartItemsAmount(0)
                .price(10)
//                .cart(cart)
                .productDetails(new ProductDetails("Gazeta wyborcza", "giga super gazeta!"))
                .productCategory(c.get(0))
                .build();
        Product product2 = Product.builder()
                .productId(2)
                .productName("Ksiazka")
                .amount(5)
                .cartItemsAmount(0)
                .price(10)
//                .cart(cart)
                .productDetails(new ProductDetails("Ksiazka do języka Angielskiego poziom C1", "SUPER GIGA CHAD KSIAZKA!!"))
                .productCategory(c.get(0))
                .build();
        Product product3 = Product.builder()
                .productId(3)
                .productName("Laptop")
                .amount(3)
                .cartItemsAmount(0)
                .price(12)
                .productCategory(c.get(0))
                .productDetails(new ProductDetails("Laptop HP", "GIGA OP LAPEK"))
                .build();
        Product product4 = Product.builder()
                .productId(4)
                .productName("Komputer")
                .amount(1)
                .cartItemsAmount(0)
                .price(1200)
                .productDetails(new ProductDetails("Komputer Lenovo","GIGA Op kompek"))
                .productCategory(c.get(1))
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    private Customer loadCustomer(Cart cart) {

        Customer customer = Customer.builder()
                .customerId(1)
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
                .customerId(2)
                .name("tescik")
                .surename("siemanko")
                .email("123@123")
                .gender("male")
                .dateOfBirth(LocalDate.of(2002, 7, 5))
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode("123"))
//                .cart(cart)
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
        return List.of(category1,category2);
    }
    private void loadOrder(Customer customer, Cart cart) {
//        Address address1 = Address.builder()
//                .id(1)
//                .street("Zielona")
//                .city("Warszawa")
//                .country("Polska")
//                .zipCode("21-310")
//                .build();
//        Order order = Order.builder()
//                .orderId(1)
//                .orderDate(LocalDate.of(2023,5,17))
//                .orderStatus(OrderStatus.PROCESSING)
//                .total(2000L)
//                .address(address1)
//                .customer(customer)
//                .cart(cart)
//                .build();
//        orderRepository.save(order);
    }

}
