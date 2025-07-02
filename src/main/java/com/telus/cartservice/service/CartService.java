package com.telus.cartservice.service;

import com.telus.cartservice.dto.CartItemDTO;
import com.telus.cartservice.dto.mapper.CartItemMapper;
import com.telus.cartservice.model.CartItem;
import com.telus.cartservice.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    // Add item to cart
    public Optional<CartItemDTO> addItemToCart(CartItemDTO dto) {
        // In a real microservice, you would call user-service and product-service here to validate
        CartItem cartItem = CartItemMapper.toEntity(dto);
        CartItem saved = cartItemRepository.save(cartItem);
        return Optional.of(CartItemMapper.toDTO(saved));
    }

    // Get cart items by user ID
    public List<CartItemDTO> getCartItemsByUser(String userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Update cart item
    public CartItemDTO updateCartItem(String itemId, CartItemDTO dto) {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(itemId);
        if (cartItemOpt.isEmpty()) {
            return null;
        }
        CartItem cartItem = cartItemOpt.get();
        if (dto.getQuantity() > 0) {
            cartItem.setQuantity(dto.getQuantity());
        }
        if (dto.getPrice() != null) {
            cartItem.setPrice(dto.getPrice());
        }
        if (dto.getUserId() != null) {
            cartItem.setUserId(dto.getUserId());
        }
        if (dto.getProductId() != null) {
            cartItem.setProductId(dto.getProductId());
        }
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return CartItemMapper.toDTO(updatedCartItem);
    }

    // Remove item from cart
    public boolean removeItem(String id) {
        return cartItemRepository.findById(id).map(item -> {
            cartItemRepository.delete(item);
            return true;
        }).orElse(false);
    }

    // Clear cart for a user
    public void clearCartByUser(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
} 