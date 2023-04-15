package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.repositories.ProductCategoryRepository;
import com.bilskik.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        List<Product> toReturn = List.copyOf(list);
        for(int i=0; i<list.size(); i++) {
            toReturn.get(i).setProductCategory(list.get(i).getProductCategory());
        }
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i).getProductCategory().getCategory());
        }
        return toReturn;
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
}
