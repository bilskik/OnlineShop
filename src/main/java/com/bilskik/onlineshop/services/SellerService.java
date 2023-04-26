package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.auth.JwtService;
import com.bilskik.onlineshop.dto.SellerDTO;
import com.bilskik.onlineshop.entities.Seller;
import com.bilskik.onlineshop.http.AuthenticationResponse;
import com.bilskik.onlineshop.http.RegisterRequest;
import com.bilskik.onlineshop.repositories.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    public AuthenticationResponse register(RegisterRequest request) {
        Seller seller = Seller.builder()
                .name(request.getName())
                .surename(request.getSurename())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        sellerRepository.save(seller);
        String jwtToken = jwtService.generateToken(seller);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Seller seller = sellerRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(seller);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public List<SellerDTO> getAllSellers() {
        List<Seller> seller = sellerRepository.findAll();
        return seller.stream().
                map(this::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }
    public SellerDTO getSellerWithId(int id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return convertFromEntityToDTO(seller.get());
    }
    public SellerDTO convertFromEntityToDTO(Seller seller) {
        return modelMapper.map(seller,SellerDTO.class);
    }
}
