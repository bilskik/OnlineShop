package com.bilskik.onlineshop.services;

import com.bilskik.onlineshop.dto.OrderDTO;
import com.bilskik.onlineshop.entities.Order;
import com.bilskik.onlineshop.mapper.MapperImpl;
import com.bilskik.onlineshop.mapper.OrderMapper;
import com.bilskik.onlineshop.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public OrderDTO getOrder(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) {
            throw new NoSuchElementException("Order does not exist!");
        }
        Field[] fields = order.get().getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(order.get());
                log.info(field.getName() + " " + value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            log.info(field.getName());
        }
        return orderMapper.toDTO(order.get());
//        return order.get();
    }
}
