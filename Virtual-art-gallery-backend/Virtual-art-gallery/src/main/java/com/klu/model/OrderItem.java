package com.klu.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artwork artwork;


    private int quantity;
    private double price;
}