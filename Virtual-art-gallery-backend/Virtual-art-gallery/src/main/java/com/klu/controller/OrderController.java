package com.klu.controller;

import com.klu.model.Order;
import com.klu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // Link items to the order manually for Hibernate
        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }
        return orderService.createOrder(order);
    }


    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}