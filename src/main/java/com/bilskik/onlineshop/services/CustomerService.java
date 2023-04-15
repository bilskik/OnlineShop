package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.jwtAuthentications.authEntities.JwtService;
import com.bilskik.onlineshop.jwtAuthentications.authEntities.AuthenticationRequest;
import com.bilskik.onlineshop.jwtAuthentications.authEntities.AuthenticationResponse;
import com.bilskik.onlineshop.jwtAuthentications.authEntities.RegisterRequest;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Role;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        Customer user = Customer.builder()
                .name(request.getName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        customerRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Customer user = customerRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
