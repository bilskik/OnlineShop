package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.http.AuthenticationRequest;
import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.dto.CustomerDTO;
import com.bilskik.onlineshop.services.CustomerService;
import jakarta.validation.Valid;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.logging.Logger;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
//        System.out.println("request.getEmail() = " + request.getEmail());
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> auth(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        logger.info("Request email : " + request.getEmail());
//        System.out.println("SIEMAAAAAAAAAAAA!");
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return new ResponseEntity<>(service.getCustomersList(), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(service.getCustomerById(id), HttpStatusCode.valueOf(200));
    }


}
