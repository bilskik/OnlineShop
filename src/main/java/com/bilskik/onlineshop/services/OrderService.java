package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.dto.AddressDTO;
import com.bilskik.onlineshop.dto.OrderDTO;
import com.bilskik.onlineshop.dto.UserEmailDTO;
import com.bilskik.onlineshop.embedded.Address;
import com.bilskik.onlineshop.entities.Cart;
import com.bilskik.onlineshop.entities.Customer;
import com.bilskik.onlineshop.entities.Order;
import com.bilskik.onlineshop.exception.NoCustomerException;
import com.bilskik.onlineshop.exception.NoEmailException;
import com.bilskik.onlineshop.exception.NoOrderException;
import com.bilskik.onlineshop.mapper.MapperImpl;
import com.bilskik.onlineshop.mapper.OrderMapper;
import com.bilskik.onlineshop.repositories.CustomerRepository;
import com.bilskik.onlineshop.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserEmailDTO userEmailDTO;

    public OrderDTO getOrder() {
        Optional<Customer> customer = customerRepository.findByEmail(userEmailDTO.getEmail());
        if(customer.isEmpty()) {
            throw new NoEmailException("Email doesn't exist!");
        }
        int customerId = customer.get().getCustomerId();
        Optional<Order> order = orderRepository.findByCustomerId(customerId);
        if(order.isEmpty()) {
            return null;
        } else {
            return orderMapper.toDTO(order.get());
        }
    }
    public OrderDTO saveOrder(Order order) {
        Optional<Customer> customer = customerRepository.findByEmail(userEmailDTO.getEmail());
        if(customer.isEmpty()) {
            throw new NoEmailException("Email doesn't exist!");
        }
        if(order == null) {
            throw new NoOrderException("Order does not exist!");
        }
        order.setCustomer(customer.get());
        order.setCart(customer.get().getCart());
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }
    @Transactional
    public Void updateAddress(Order responseOrder) {

        Optional<Customer> customer = customerRepository.findByEmail(userEmailDTO.getEmail());
        if(responseOrder == null) {
            throw new NoSuchElementException("Passed address is null!");
        }
        if(customer.isEmpty()) {
            throw new NoSuchElementException("There is no customer!");
        }
        int orderId = customer.get().getOrder().getOrderId();
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) {
            throw new NoSuchElementException("There is no order available!");
        }
        order.get().setAddress(responseOrder.getAddress());
        order.get().setPaymentType(responseOrder.getPaymentType());
        orderRepository.save(order.get());
        return null;
    }
}
