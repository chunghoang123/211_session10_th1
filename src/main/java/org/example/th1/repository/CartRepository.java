package org.example.th1.repository;

import org.example.th1.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CartRepository {

    private final List<CartItem> cartItems = new ArrayList<>();

    public CartItem save(CartItem item) {
        cartItems.add(item);
        return item;
    }

    public List<CartItem> findAll() {
        return cartItems;
    }

    public Optional<CartItem> findById(Long id) {
        return cartItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public List<CartItem> findByUserId(String userId) {
        return cartItems.stream()
                .filter(item -> item.getUserId().equals(userId))
                .toList();
    }

    public Optional<CartItem> findByUserIdAndProductId(
            String userId,
            String productId
    ) {
        return cartItems.stream()
                .filter(item ->
                        item.getUserId().equals(userId)
                                && item.getProductId().equals(productId))
                .findFirst();
    }

    public void deleteById(Long id) {
        cartItems.removeIf(item -> item.getId().equals(id));
    }
}