package com.bilskik.onlineshop.entities;

import com.bilskik.onlineshop.embedded.Address;
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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate orderDate;
    public String paymentType;
    @Embedded
    public Address address;
    public Long total;
    @OneToOne
    @JoinColumn(
            name = "customerId",
            referencedColumnName = "customerId"
    )
    public Customer customer;
    @OneToOne
    @JoinColumn(
            name = "cartId",
            referencedColumnName = "cartId"
    )
    public Cart cart;

}
