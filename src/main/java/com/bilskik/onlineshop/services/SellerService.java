package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.entities.Seller;
import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    public ServiceRepository serviceRepository;

    public AuthenticationResponse register(RegisterRequest request) {
//        Seller seller = Seller.builder()
//                .name(request.getName())
//                .surename(request.getSurename()
//                .build();
        return null;
    }
    public AuthenticationResponse login(RegisterRequest request) {
        return null;
    }
}
