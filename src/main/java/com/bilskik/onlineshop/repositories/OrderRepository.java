package com.bilskik.onlineshop.repositories;

import com.bilskik.onlineshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Modifying
    @Query(value = "INSERT INTO orders(order_id, total,cart_id,customer_id) VALUES (:order_id, :total, :cart_id, :customer_id)",
            nativeQuery = true)
    void saveOrder(
                    @Param("order_id") int orderId,
                    @Param("total") Long total,
                   @Param("cart_id") int cartId,
                   @Param("customer_id") int customerId
    );


    @Query(value = "Select * from orders o where o.customer_id = customer_id",nativeQuery = true)
    Optional<Order> findByCustomerId(@Param("customer_id") int customerId);

//    @Modifying
//    @Query(value = "UPDATE orders o SET o.country = :country, o.city = :city, o.street = :street, o.zip_code = :zip_code " +
//            "where o.order_id = :order_id" )
//    int updateOrder(@Param("order_id") int orderId,
//                    @Param("country") String country,
//                    @Param("city") String city,
//                    @Param("street") String street,
//                    @Param("zip_code") String zipCode
//    );
}
