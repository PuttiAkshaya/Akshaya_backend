package com.klu.service.impl;

import com.klu.model.Artwork;
import com.klu.model.Cart;
import com.klu.model.User;
import com.klu.repository.ArtworkRepository;
import com.klu.repository.CartRepository;
import com.klu.repository.UserRepository;
import com.klu.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    // ✅ FIXED ADD TO CART
    @Override
    public Cart addToCart(Cart cart) {

        // fetch user
        User user = userRepository.findById(cart.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // fetch artwork
        Artwork artwork = artworkRepository.findById(cart.getArtwork().getId())
                .orElseThrow(() -> new RuntimeException("Artwork not found"));

        // set proper objects
        cart.setUser(user);
        cart.setArtwork(artwork);

        return cartRepository.save(cart);
    }

    // ✅ FIXED GET CART
    @Override
    public List<Cart> getCartByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user);
    }

    // ✅ REMOVE ITEM
    @Override
    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}