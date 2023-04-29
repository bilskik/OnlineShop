package com.bilskik.onlineshop.controllers;

import com.bilskik.onlineshop.auth.JwtService;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.entities.User;
import com.bilskik.onlineshop.repositories.ProductRepository;
import com.bilskik.onlineshop.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private ProductService productService;
    @Test
    void getProduct() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9" +
                ".eyJzdWIiOiJrYW1pbEBnbWFpbC5jb20iLCJpYXQiOjE2ODI2ODI0MTEsImV4cCI6MTY4NDEyMjQxMX0" +
                ".lknU1TDOdLfoYhgKbTWZMMc5Eso1hxjrLDCd8SGkEnQ";
        when(jwtService.extractUsername(anyString())).thenReturn("testuser");
        when(jwtService.isTokenValid(anyString(), any(User.class))).thenReturn(true);

        RequestBuilder request = MockMvcRequestBuilders.get("/products").header("Authorization", "Bearer " + token);
        MvcResult result = mvc.perform(request).andReturn();
        List<Product> list = productRepository.findAll();
        System.out.println("LIST:");
        list.forEach(System.out::println);

        assertEquals(list.toString(),result.getResponse().getContentAsString());
    }

}