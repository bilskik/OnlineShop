package com.bilskik.onlineshop.config;

import com.bilskik.onlineshop.dto.CustomerDTO;
import com.bilskik.onlineshop.dto.OrderDTO;
import com.bilskik.onlineshop.dto.ProductDTO;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Order;
import com.bilskik.onlineshop.entities.Product;
import com.bilskik.onlineshop.mapper.MapperImpl;
import com.bilskik.onlineshop.mapper.OrderMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public MapperImpl<Customer,CustomerDTO> customerMapper() {
        return new MapperImpl<>(Customer.class, CustomerDTO.class);
    }
    @Bean
    public MapperImpl<Product, ProductDTO> productMapper() {
        return new MapperImpl<>(Product.class,ProductDTO.class);
    }
}
