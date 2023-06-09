package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.dto.SellerDTO;
import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {
    @Autowired
    public SellerService sellerService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ) {
        return new ResponseEntity<>(sellerService.register(request), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(sellerService.authenticate(request), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        return new ResponseEntity<>(sellerService.getAllSellers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSellerWithId(@PathVariable int id) {
        return new ResponseEntity<>(sellerService.getSellerWithId(id), HttpStatus.OK);
    }

}
