package com.bilskik.onlineshop.config;

import com.bilskik.onlineshop.dto.CustomerDTO;
import com.bilskik.onlineshop.entities.Customer;
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
}
