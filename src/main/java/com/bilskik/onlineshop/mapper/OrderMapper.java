package com.bilskik.onlineshop.mapper;

import com.bilskik.onlineshop.dto.OrderDTO;
import com.bilskik.onlineshop.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends MapperImpl<Order, OrderDTO> {
    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = getModelMapper().map(order,OrderDTO.class);
        orderDTO.setCustomer(orderDTO.getCustomer());
        orderDTO.setProductList(order.getCart().getProductList());
        return orderDTO;
    }
}
