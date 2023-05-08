package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.dto.OrderDTO;
import com.bilskik.onlineshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable int orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatusCode.valueOf(200));
    }
}
