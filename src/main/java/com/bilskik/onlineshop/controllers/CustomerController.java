package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.jwtAuthentications.authEntities.AuthenticationRequest;
import com.bilskik.onlineshop.jwtAuthentications.authEntities.AuthenticationResponse;
import com.bilskik.onlineshop.jwtAuthentications.authEntities.RegisterRequest;
import com.bilskik.onlineshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> auth(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
