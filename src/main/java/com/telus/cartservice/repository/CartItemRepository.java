package com.telus.cartservice.repository;

import com.telus.cartservice.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByUserId(String userId);
    void deleteByUserId(String userId);
} 