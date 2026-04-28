package com.klu.service;

import com.klu.model.Cart;
import java.util.List;

public interface CartService {
    Cart addToCart(Cart cart);
    List<Cart> getCartByUser(Long userId);
    void removeFromCart(Long id);
}