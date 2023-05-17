package com.bilskik.onlineshop.dto;


import com.bilskik.onlineshop.embedded.Address;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.enumeration.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @JsonProperty("orderDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date of order cannot be null!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    private Long total;
    private Address address;
    private String paymentType;
    private CustomerDTO customer;
    private List<Product> productList;
}
