package com.bilskik.onlineshop.repositories;

import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.ProductCategory;
import com.bilskik.onlineshop.embedded.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByproductName(String productName);

    @Modifying
    @Query("update Product p  set p.productName = :productName, p.amount = :amount, p.cartItemsAmount = :cartItemAmount, p.price = :price, " +
            "p.productDetails = :productDetails, p.productCategory = :productCategory where p.productId = :productId")
    int updateProduct(int productId,String productName, int amount,int cartItemAmount, double price,
                      ProductDetails productDetails, ProductCategory productCategory);
}
