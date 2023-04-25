package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {
    @Autowired
    public SellerService sellerService;
    @PostMapping("/seller/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ) {
        return new ResponseEntity<AuthenticationResponse>(sellerService.register(request),HttpStatusCode.valueOf(200));
    }
    @PostMapping("/seller/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<AuthenticationResponse>(sellerService.login(request), HttpStatusCode.valueOf(200));
    }

}
