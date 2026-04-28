package com.klu.service;

import com.klu.model.Order;
import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);

    List<Order> getOrdersByUser(Long userId);

    Order getOrderById(Long orderId);
    
    void deleteOrder(Long orderId);

    List<Order> getAllOrders();

    Order createOrder(Order order);
}

