package com.bilskik.onlineshop.mapper;

import com.bilskik.onlineshop.dto.CartDTO;
import com.bilskik.onlineshop.entities.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper extends MapperImpl<Cart, CartDTO> {
    @Override
    public CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = getModelMapper().map(cart,CartDTO.class);
        cartDTO.setProductList(cart.getProductList());
        return cartDTO;
    }
}
