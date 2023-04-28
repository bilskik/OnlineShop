package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.auth.JwtService;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.http.AuthenticationRequest;
import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.dto.CustomerDTO;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Role;
import com.bilskik.onlineshop.mapper.Mapper;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    private final ModelMapper modelMapper;
    private final Mapper<Customer,CustomerDTO> mapper;
    public AuthenticationResponse register(RegisterRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .surename(request.getSurename())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .cart(new Cart())
                .build();
        customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
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

    public List<CustomerDTO> getCustomersList() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no such an element!");
        }
        return mapper.toDTO(customer.get());
    }

//    private CustomerDTO convertEntityToDTO(Customer customer) {
//        return modelMapper.map(customer,CustomerDTO.class);
//    }
}
