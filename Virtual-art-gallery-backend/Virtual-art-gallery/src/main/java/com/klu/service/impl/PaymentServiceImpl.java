package com.klu.service.impl;

import com.klu.model.Order;
import com.klu.model.Payment;
import com.klu.repository.OrderRepository;
import com.klu.repository.PaymentRepository;
import com.klu.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment processPayment(Payment payment) {

        // ✅ Fetch order from DB
        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // ✅ Set full order
        payment.setOrder(order);

        // ✅ Set amount from order
        payment.setAmount(order.getTotalPrice());

        // ✅ Set status
        payment.setStatus("SUCCESS");

        // ✅ Keep method (UPI / CARD etc.)
        payment.setMethod(payment.getMethod());

        // ✅ Generate transaction id
        payment.setTransactionId("TXN" + System.currentTimeMillis());

        return paymentRepository.save(payment);
    }
}