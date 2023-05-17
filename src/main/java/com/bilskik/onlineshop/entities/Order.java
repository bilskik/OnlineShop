package com.bilskik.onlineshop.entities;

import com.bilskik.onlineshop.embedded.Address;
import com.bilskik.onlineshop.enumeration.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "seq_order",
            sequenceName = "seq_order",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "seq_order",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "order_id")
    public int orderId;
    @JsonProperty("orderDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
//    @NotNull(message = "Date of order cannot be null!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;
    @Embedded
    private Address address;
    private Long total;
    @OneToOne
    @JoinColumn(
            name = "customerId",
            referencedColumnName = "customerId"
    )
    private Customer customer;
    @OneToOne
    @JoinColumn(
            name = "cartId",
            referencedColumnName = "cartId"
    )
    private Cart cart;

}
