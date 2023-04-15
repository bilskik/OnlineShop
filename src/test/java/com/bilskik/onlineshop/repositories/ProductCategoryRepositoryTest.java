package com.bilskik.onlineshop.repositories;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductCategoryRepositoryTest {
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
    public void saveCategory() {
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
    public void getCategory() {
        productCategoryRepository.findByCategory("Ksiazka");
    }
    @Test
    public void getCategoryWithProducts() {
        Product p1 = createProduct("harry potter",1,100);
        Product p2 = createProduct("opowiesci z narnii",1,60);
        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(p1);
        expectedProductList.add(p2);
        Optional<ProductCategory> productCategory = productCategoryRepository.findByCategory("Ksiazka");
        if(productCategory.isPresent()) {
            List<Product> productList = productCategory.get().getProductList();
            if(productList.size() != expectedProductList.size()) {
                assert false;
            }
            else {
                for(int i=0; i<expectedProductList.size(); i++) {
                    assertEquals(expectedProductList.get(i).getProductName(),productList.get(i).getProductName());
                }
            }
        }
    }
}