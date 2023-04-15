package com.bilskik.onlineshop.repositories;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    private Product createProduct(String name, int amount, int price) {
        Product p = Product.builder()
                .productName(name)
                .amount(amount)
                .price(price)
                .build();
        return p;
    }
    private ProductCategory createProductCategory(String category) {
        ProductCategory productCategory = ProductCategory.builder()
                .category(category)
                .build();
        return productCategory;
    }
    @Test
    public void saveProductsWithProductCategory() {

        Product p1 = createProduct("harry potter",1,100);
        Product p2 = createProduct("opowiesci z narnii",1,60);
        ProductCategory productCategory = createProductCategory("Ksiazka");
        p1.setProductCategory(productCategory);
        p2.setProductCategory(productCategory);
        productCategory.addProduct(p1);
        productCategory.addProduct(p2);
        productCategoryRepository.save(productCategory);
    }
    @Test
    public void getProductWithSpecifiedCategory() {
        Product expected = createProduct("harry potter",1,100);
        ProductCategory productCategory = createProductCategory("Ksiazka");
        expected.setProductCategory(productCategory);
        Optional<Product> p = productRepository.findById(1);
        if(p.isPresent()) {
            Product toTest = p.get();
            assertEquals(expected.getProductCategory().getCategory(),toTest.getProductCategory().getCategory());
        }
    }
    @Test
    public void getAllProductsWithSpecifiedCategory() {
        Product expected1 = createProduct("harry potter",1,100);
        Product expected2 = createProduct("opowiesci z narnii",1,60);
        ProductCategory productCategory = createProductCategory("Ksiazka");
        expected1.setProductCategory(productCategory);
        expected2.setProductCategory(productCategory);
        Optional<Product> p1 = productRepository.findByproductName("harry potter");
        Optional<Product> p2 = productRepository.findByproductName("opowiesci z narnii");
        if(p1.isPresent() && p2.isPresent()) {
            assertEquals(expected1.getProductCategory().getCategory(),p1.get().getProductCategory().getCategory());
            assertEquals(expected2.getProductCategory().getCategory(),p2.get().getProductCategory().getCategory());
        }
    }
    @Test
    public void getProducts() {
        Product expected = createProduct("harry potter",1,100);
        Optional<Product> optional = productRepository.findById(1);
        Product fromDb = null;
        if(optional.isPresent()) {
            fromDb = optional.get();
        }
        assertEquals(expected.getProductName(), fromDb.getProductName());
        assertEquals(expected.getAmount(), fromDb.getAmount());
        assertEquals(expected.getPrice(), fromDb.getPrice());
    }
}