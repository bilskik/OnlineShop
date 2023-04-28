package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.repositories.ProductCategoryRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        if(list.isEmpty()) {
            throw new NoSuchElementException("There is no product available!");
        }
        return list;
    }

    public Product saveProduct(Product product) {
        if(product.getProductCategory() != null) {
            ProductCategory productCategory = product.getProductCategory();
            Optional<ProductCategory> optionalProductCategory = productCategoryRepository
                    .findByCategory(productCategory.getCategory());
            if(optionalProductCategory.isPresent()) {
                product.setProductCategory(optionalProductCategory.get());
            }
        }
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NoSuchElementException("There is no product available in DB!");
        }
        product.get().setCart(null);
        productRepository.save(product.get());
        productRepository.deleteById(productId);
    }

    @Transactional
    public Product updateProduct(Product product) {
        int result = productRepository.updateProduct(product.getProductId(), product.getProductName()
                ,product.getAmount(),product.getPrice(),product.getProductDetails(), product.getProductCategory());
        if(result == 1) {
            Optional<Product> productOptional = productRepository.findById(product.getProductId());
            if(productOptional.isPresent()) {
                return productOptional.get();
            }
            else {
                throw new NoSuchElementException("Product was not found!");
            }
        }
        else {
            return product;
        }
    }
}
