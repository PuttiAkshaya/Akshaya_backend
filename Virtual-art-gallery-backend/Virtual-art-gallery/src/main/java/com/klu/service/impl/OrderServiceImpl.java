package com.klu.service.impl;

import com.klu.model.Cart;
import com.klu.model.Order;
import com.klu.model.User;
import com.klu.repository.CartRepository;
import com.klu.repository.OrderRepository;
import com.klu.repository.UserRepository;
import com.klu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private com.klu.repository.OrderItemRepository orderItemRepository;

    // ✅ PLACE ORDER (MAIN LOGIC)
    @Override
    public Order placeOrder(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Cart> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;
        for (Cart item : cartItems) {
            total += item.getArtwork().getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(total);
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        // ✅ Save OrderItems
        for (Cart cartItem : cartItems) {
            com.klu.model.OrderItem orderItem = new com.klu.model.OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setArtwork(cartItem.getArtwork());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getArtwork().getPrice());
            orderItemRepository.save(orderItem);
        }

        // clear cart
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }


    // ✅ GET USER ORDERS
    @Override
    public List<Order> getOrdersByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user);
    }

    // ✅ TRACK ORDER
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}

