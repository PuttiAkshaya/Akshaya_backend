package com.klu.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
   
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private User artist;

    private int piecesCount = 1;
    
    @Column(columnDefinition = "boolean default false")
    private boolean approved = false;

    @Column(columnDefinition = "boolean default false")
    private boolean sold = false;
}
