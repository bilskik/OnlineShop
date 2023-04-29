//package com.bilskik.onlineshop.seed;
//
//import com.bilskik.onlineshop.entities.*;
//import com.bilskik.onlineshop.repositories.CustomerRepository;
//import com.bilskik.onlineshop.repositories.ProductRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureTestDatabase
//@Transactional
//public class DatabaseSeeder {
//    @Autowired
////    private EntityManagerFactory entityManagerFactory;
//    private TestEntityManager testEntityManager;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @BeforeEach
//    public void seedDatabase() {
//        productRepository.deleteAll();
//        customerRepository.deleteAll();
//        loadData();
//    }
//
//    private void loadData() {
//        Cart cart = loadCart();
//        List<ProductCategory> c = createCategory();
//        loadProduct(cart,c);
//        loadCustomer(cart);
//    }
//
//    private Cart loadCart() {
//        Cart cart = Cart.builder()
//                .cartId(1)
//                .productList(new ArrayList<>())
//                .build();
//        return cart;
//    }
//
//    private void loadProduct(Cart cart, List<ProductCategory> c) {
//        //not added details and product category
//        Product product1 = Product.builder()
//                .productId(1)
//                .productName("Gazeta")
//                .amount(1)
//                .price(10)
//                .cart(cart)
//                .productCategory(c.get(0))
//                .build();
//        Product product2 = Product.builder()
//                .productId(2)
//                .productName("Ksiazka")
//                .amount(1)
//                .price(10)
//                .cart(cart)
//                .productCategory(c.get(0))
//                .build();
//        Product product3 = Product.builder()
//                .productId(3)
//                .productName("Pismo")
//                .amount(1)
//                .price(12)
//                .productCategory(c.get(0))
//                .build();
//        Product product4 = Product.builder()
//                .productId(4)
//                .productName("Komputer")
//                .amount(1)
//                .price(1200)
//                .productCategory(c.get(1))
//                .build();
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//        productRepository.save(product4);
//    }
//
//    private void loadCustomer(Cart cart) {
//
//        Customer customer = Customer.builder()
//                .customerId(1)
//                .name("Kamil")
//                .surename("Bilski")
//                .email("kamil@gmail.com")
//                .gender("mezczyzna")
//                .dateOfBirth(LocalDate.of(2002, 7, 5))
//                .role(Role.CUSTOMER)
//                .password("123")
//                .cart(cart)
//                .build();
//        customerRepository.save(customer);
//    }
//    private List<ProductCategory> createCategory() {
//        ProductCategory category1 = ProductCategory.builder()
//                .categoryId(1)
//                .category("Materiały Pismienne")
//                .build();
//        ProductCategory category2 = ProductCategory.builder()
//                .categoryId(2)
//                .category("RTV-AGD")
//                .build();
//        return List.of(category1,category2);
//    }
//}
