package com.telus.cartservice.dto.mapper;

import com.telus.cartservice.dto.CartItemDTO;
import com.telus.cartservice.model.CartItem;

public class CartItemMapper {
    public static CartItemDTO toDTO(CartItem cartItem) {
        if (cartItem == null) return null;
        return new CartItemDTO(
            cartItem.getId(),
            cartItem.getUserId(),
            cartItem.getProductId(),
            cartItem.getQuantity(),
            cartItem.getPrice()
        );
    }
    public static CartItem toEntity(CartItemDTO dto) {
        if (dto == null) return null;
        CartItem cartItem = new CartItem();
        cartItem.setId(dto.getId());
        cartItem.setUserId(dto.getUserId());
        cartItem.setProductId(dto.getProductId());
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setPrice(dto.getPrice());
        return cartItem;
    }
} 